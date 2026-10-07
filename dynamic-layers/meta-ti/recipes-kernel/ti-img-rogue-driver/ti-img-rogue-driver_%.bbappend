FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append = "${@bb.utils.contains('TI_PREFERRED_BSP', 'ng_gem_o1', ' \
    file://0001-rogue-fix-pfn_t-for-linux-7.x.patch \
    file://0002-kernel-comp.patch \
', '', d)}"