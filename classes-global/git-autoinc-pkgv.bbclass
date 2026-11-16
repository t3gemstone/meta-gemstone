# Make package versions of git based recipes sortable without a PR service.
#
# When PV contains "+git" (e.g. PV = "6.12.24+git"), OE appends
# "AUTOINC+<short hash>" to PKGV. AUTOINC is only turned into an increasing
# number when a PR service (PRSERV_HOST) is configured, otherwise it becomes
# "0" and the packages are versioned like:
#
#     6.12.24+git0+6267aeaee4-r0
#
# dpkg/apt then decide between two builds by comparing the commit hashes as
# strings, so a newer SRCREV whose hash happens to sort lower is treated as a
# downgrade, and an older build may be offered as an upgrade.
#
# This class replaces AUTOINC with the number of commits reachable from
# SRCREV ("git rev-list --count"), giving versions such as:
#
#     6.12.24+git1386201+6267aeaee4-r0
#
# The number only depends on SRCREV, so it is the same on every build machine
# and no PR database has to be kept between (CI) builds. As long as SRCREV
# moves forward in history the number, and thus the package version, grows.
# Recipes with several git repositories in SRC_URI use the sum of the counts.
#
# If a PR service is configured it keeps handling AUTOINC and this class does
# nothing. Note that the PR service starts counting from 0, so switching to it
# later needs a PE bump on the affected recipes to keep them upgradable.

def git_autoinc_commit_count(d):
    """
    Return the total number of commits reachable from the SRCREVs of all git
    repositories in SRC_URI, or None if it cannot be determined.
    """
    import os
    import shlex

    fetcher = bb.fetch2.Fetch(d.getVar('SRC_URI').split(), d)
    total = None

    for ud in fetcher.ud.values():
        if not ud.method.supports_srcrev():
            continue

        if ud.type not in ('git', 'gitsm'):
            bb.debug(1, "git-autoinc-pkgv: %s is not a git repository, leaving AUTOINC alone" % ud.url)
            return None

        for name in ud.names:
            rev = ud.revisions[name]

            if not os.path.isdir(ud.clonedir):
                bb.warn("git-autoinc-pkgv: %s does not exist, cannot count the commits of %s" % (ud.clonedir, rev))
                return None

            try:
                output = bb.fetch2.runfetchcmd("%s rev-list --count %s --" % (ud.basecmd, shlex.quote(rev)),
                                               d, quiet=True, workdir=ud.clonedir)
                count = int(output.strip())
            except (bb.fetch2.FetchError, ValueError) as e:
                bb.warn("git-autoinc-pkgv: cannot count the commits of %s in %s: %s" % (rev, ud.clonedir, e))
                return None

            total = (total or 0) + count

    return total

# package_setup_pkgv has already turned AUTOINC into ${PRSERV_PV_AUTOINC} and
# package_convert_pr_autoinc has just set it to a placeholder, which
# do_packagedata would replace with "0" when no PR service is configured.
python package_convert_pr_autoinc:append() {
    git_autoinc_resolve(d)
}

def git_autoinc_resolve(d):
    if bb.utils.to_boolean(d.getVar('PRSERV_ACTIVE')) or d.getVar('PRSERV_HOST_' + d.getVar('PN')):
        return

    if '@PRSERV_PV_AUTOINC@' not in (d.getVar('PKGV') or ''):
        return

    count = git_autoinc_commit_count(d)
    if count is None:
        bb.warn("git-autoinc-pkgv: falling back to AUTOINC=0, package version %s may not sort correctly"
                % d.getVar('PKGV').replace('@PRSERV_PV_AUTOINC@', '0'))
        return

    d.setVar('PRSERV_PV_AUTOINC', str(count))
    bb.debug(1, "git-autoinc-pkgv: PKGV is %s" % d.getVar('PKGV'))
