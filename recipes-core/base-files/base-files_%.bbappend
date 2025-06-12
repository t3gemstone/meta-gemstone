FILESEXTRAPATHS:prepend := "${@bb.utils.contains('DISTRO_GEMSTONE_QT_INCLUDED', '1', '${THISDIR}/files:', '', d)}"
