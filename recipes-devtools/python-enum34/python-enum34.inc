DESCRIPTION = "An enumeration is a set of symbolic names (members) bound to \
unique, constant values. Within an enumeration, the members can be compared \
by identity, and the enumeration itself can be iterated over."
SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${S}/PKG-INFO;md5=834b337925aa0680f3fed1a5cc71c01d"

PYPI_PACKAGE = "enum34"
PYPI_PACKAGE_EXT = "zip"
BBCLASSEXTEND = "native"

SRC_URI[md5sum] = "61ad7871532d4ce2d77fac2579237a9e"
SRC_URI[sha256sum] = "2d81cbbe0e73112bdfe6ef8576f2238f2ba27dd0d55752a776c41d38b7da2850"

inherit pypi

#RDEPENDS_${PN} += "\
#    ${PYTHON_PN}-netserver \
#    ${PYTHON_PN}-subprocess \
#    ${PYTHON_PN}-shell \
#"
#RDEPENDS_${PN}_class-native = ""
