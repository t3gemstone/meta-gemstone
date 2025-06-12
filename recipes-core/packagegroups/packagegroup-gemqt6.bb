DESCRIPTION = "T3 Gemstone Qt6 Packagegroup"
SUMMARY = ""

# Prevents the dynamic renaming of packages
# (which throws an error in newer Bitbake versions)
PACKAGE_ARCH = "${TUNE_PKGARCH}"

inherit packagegroup

PACKAGES = " \
    ${PN} \
    ${PN}-tools \
    ${PN}-translations \
"

# Install fonts
QT5_FONTS = "ttf-dejavu-common ttf-dejavu-sans ttf-dejavu-sans-mono ttf-dejavu-serif "

QT5_QTQUICK3D = "qtquick3d"

RDEPENDS:${PN} = " \
    qtbase \
    qtcharts \
    qtconnectivity \
    qtdeclarative \
    qtimageformats \
    qtlocation \
    qtmqtt \
    qtmultimedia \
    qtnetworkauth \
    qtremoteobjects \
    qtserialbus \
    qtserialport \
    qtsvg \
    qttools \
    qtvirtualkeyboard \
    qtwebchannel \
    qtwebsockets \
    qtsensors \
    qtshadertools \
    qtwebengine \
    qtscxml \
    qtpdf \
    qt5compat \
    qt3d \
    ${QT5_FONTS} \
    ${QT5_QTQUICK3D} \
"

RDEPENDS:${PN}-tools = " \
    qtdeclarative-tools \
    qttools-tools \
"

RDEPENDS:${PN}-translations = " \
    qttranslations-qtbase \
    qttranslations-qtconnectivity \
    qttranslations-qtlocation \
    qttranslations-qtmultimedia \
    qttranslations-qtserialport \
    qttranslations-qtwebsockets \
    qttranslations-qtwebengine \
    qttranslations-designer \
    qttranslations-linguist \
    qttranslations-assistant \ 
    qttranslations-qtdeclarative \
"
