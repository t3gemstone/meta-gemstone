FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://Static.nmconnection"

do_install:append() {
    install -d "${D}${sysconfdir}/NetworkManager/system-connections"
    install -m 0600 "${WORKDIR}/Static.nmconnection" "${D}${sysconfdir}/NetworkManager/system-connections"
}
