DESCRIPTION = "PyBluez to access Bluetooth stack"
SECTION = "devel/python"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README;beginline=51;endline=64;md5=794e59691eb9984b2ffbe3044114eb73"

PYPI_PACKAGE = "PyBluez"
PYPI_PACKAGE_EXT = "zip"
BBCLASSEXTEND = "native"

SRC_URI[md5sum] = "49dab9d5a8f0b798c8125c7f649be3cd"
SRC_URI[sha256sum] = "4ce006716a54d9d18e8186a3f1c8b12a8e6befecffe8fd5828a291fb694ce49d"

inherit pypi

RDEPENDS_${PN} += "\
    ${PYTHON_PN}-netserver \
    ${PYTHON_PN}-subprocess \
    ${PYTHON_PN}-shell \
"
RDEPENDS_${PN}_class-native = ""

DEPENDS_${PN} += " bluez5"

CFLAGS += " -I${D}${includedir}"
