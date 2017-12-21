DESCRIPTION = "PyuEye is a lean wrapper implementation of Python function \
objects that represent uEye API functions. As there is no intelligence in \
the PyuEye interface, it is as near as possible to the uEye C API and always \
up-to-date. Benefit from many Python advantages, for example the interactive \
programming of your uEye camera. Write and test small code snippets without \
the complex setup of a programming IDE with a toolchain. The PyuEye interface \
is ideal for prototyping uEye camera applications for all supported uEye \
platforms (Windows, Linux, Linux Embedded)."
SECTION = "devel/python"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://PKG_INFO;beginline=41;endline=75;md5=5551b490af454a649648dcb9132f85ce"

PYPI_PACKAGE = "pyueye"
PYPI_PACKAGE_EXT = "zip"
BBCLASSEXTEND = "native"

SRC_URI[md5sum] = "0398e0d39d241921a8290b5c891f0e63"
SRC_URI[sha256sum] = "54c389176d359259c930f22035f1c006df37b4be9a71954a7fa864d679adc3a4"

inherit pypi

do_compile_prepend(){
	# workaround since package does not come with README, which breaks the build
	touch ${S}/README.md
}

#RDEPENDS_${PN} += "\
#    ${PYTHON_PN}-netserver \
#    ${PYTHON_PN}-subprocess \
#    ${PYTHON_PN}-shell \
#"
RDEPENDS_${PN}_class-native = "python-pypandoc"
