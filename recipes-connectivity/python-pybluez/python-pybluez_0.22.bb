inherit pypi setuptools
require python-pybluez.inc

LICENSE = "MIT"
RDEPENDS_${PN} += "bluez5 bluez-hcidump "
