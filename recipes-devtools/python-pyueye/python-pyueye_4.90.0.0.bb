inherit setuptools
require python-pyueye.inc

RDEPENDS_${PN} += "\
    ueye-drivers \
	${PYTHON_PN}-enum34 \
"
