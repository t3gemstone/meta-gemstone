SUMMARY = "RPMsg Virtual Ethernet Network Driver"
DESCRIPTION = "Virtual Ethernet network driver using the Linux RPMsg framework"
HOMEPAGE = "https://github.com/t3gemstone/rpmsg-net"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

inherit module

SRC_URI = "git://github.com/t3gemstone/rpmsg-net.git;protocol=https;branch=main"

SRCREV = "2a4efe61a3551a80f1b0a1f39f16afed9089db09"

S = "${WORKDIR}/git"

EXTRA_OEMAKE += "KERNEL_SRC=${STAGING_KERNEL_DIR}"

MODULE_NAME = "rpmsg_net"

KERNEL_MODULE_AUTOLOAD += "rpmsg_net"
