# Look in the generic major.minor directory for files
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-6.12:"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SECTION = "kernel"
SUMMARY = "BeagleBoard.org Linux kernel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

COMPATIBLE_MACHINE = "beagle.*"

inherit kernel

require recipes-kernel/linux/setup-defconfig.inc
require recipes-kernel/linux/ti-kernel.inc

DEPENDS += "gmp-native libmpc-native"

KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT} ${EXTRA_DTC_ARGS}"

S = "${WORKDIR}/git"

SRC_URI += "file://0001-Added-beagley-ai-overlays.patch \
            file://0002-updated-beagley-ai-dts.patch"

# 6.12.24 version for 64-bit
SRCREV:aarch64 = "708e1e0be5126a696eaee37a2b027bc4d1f575c4"
PV:aarch64 = "6.12.24+git"
BRANCH:aarch64 = "v6.12.24-ti-arm64-r43"

KERNEL_GIT_URI = "git://github.com/beagleboard/linux.git"

KERNEL_DEVICETREE:append:beagley-ai = " \
    ti/k3-am67a-beagley-ai.dtb \
    ti/k3-am67a-beagley-ai-csi0-imx219.dtbo \
    ti/k3-am67a-beagley-ai-csi0-ov5640.dtbo \
    ti/k3-am67a-beagley-ai-csi1-imx219.dtbo \
    ti/k3-am67a-beagley-ai-dsi-rpi-7inch-panel.dtbo \
    ti/k3-am67a-beagley-ai-hdmi-dss0-dpi1.dtbo \
    ti/k3-am67a-beagley-ai-i2c1-400000.dtbo \
    ti/k3-am67a-beagley-ai-i2c1-ads1115.dtbo \
    ti/k3-am67a-beagley-ai-i2c1-ssd1306.dtbo \
    ti/k3-am67a-beagley-ai-lincolntech-185lcd-panel.dtbo \
    ti/k3-am67a-beagley-ai-mikroe-eth.dtbo \
    ti/k3-am67a-beagley-ai-mikroe-microsd.dtbo \
    ti/k3-am67a-beagley-ai-pwm-ecap0-gpio12.dtbo \
    ti/k3-am67a-beagley-ai-pwm-ecap1-gpio16.dtbo \
    ti/k3-am67a-beagley-ai-pwm-ecap1-gpio21.dtbo \
    ti/k3-am67a-beagley-ai-pwm-ecap2-gpio17.dtbo \
    ti/k3-am67a-beagley-ai-pwm-ecap2-gpio18.dtbo \
    ti/k3-am67a-beagley-ai-pwm-epwm0-gpio5-gpio12.dtbo \
    ti/k3-am67a-beagley-ai-pwm-epwm0-gpio5-gpio14.dtbo \
    ti/k3-am67a-beagley-ai-pwm-epwm0-gpio5.dtbo \
    ti/k3-am67a-beagley-ai-pwm-epwm0-gpio12.dtbo \
    ti/k3-am67a-beagley-ai-pwm-epwm0-gpio14.dtbo \
    ti/k3-am67a-beagley-ai-pwm-epwm0-gpio15-gpio12.dtbo \
    ti/k3-am67a-beagley-ai-pwm-epwm0-gpio15-gpio14.dtbo \
    ti/k3-am67a-beagley-ai-pwm-epwm0-gpio15.dtbo \
    ti/k3-am67a-beagley-ai-pwm-epwm1-gpio6-gpio13.dtbo \
    ti/k3-am67a-beagley-ai-pwm-epwm1-gpio6-gpio20.dtbo \
    ti/k3-am67a-beagley-ai-pwm-epwm1-gpio6.dtbo \
    ti/k3-am67a-beagley-ai-pwm-epwm1-gpio13.dtbo \
    ti/k3-am67a-beagley-ai-pwm-epwm1-gpio20.dtbo \
    ti/k3-am67a-beagley-ai-pwm-epwm1-gpio21-gpio13.dtbo \
    ti/k3-am67a-beagley-ai-pwm-epwm1-gpio21-gpio20.dtbo \
    ti/k3-am67a-beagley-ai-pwm-epwm1-gpio21.dtbo \
    ti/k3-am67a-beagley-ai-spi0-1cs.dtbo \
    ti/k3-am67a-beagley-ai-spi0-2cs.dtbo \
    ti/k3-am67a-beagley-ai-spidev0.dtbo \
    ti/k3-am67a-beagley-ai-uart-ttyama0.dtbo \
    ti/k3-am67a-beagley-ai-edgeai-apps.dtbo \
"
