TI_PREFERRED_BSP ?= "gem_o1"

# ==========
# gem_o1
# Kernel 6.12, u-boot 2025.04
# ==========
BSP_KERNEL_PROVIDER:bsp-gem_o1 = "linux-t3-gem-o1-rt"
BSP_KERNEL_VERSION:bsp-gem_o1 = "6.12.%"
BSP_BOOTLOADER_PROVIDER:bsp-gem_o1 = "u-boot-t3-gem-o1"
BSP_BOOTLOADER_VERSION:bsp-gem_o1 = "2025.%"

BSP_SGX_DRIVER_PROVIDER:bsp-gem_o1 = "ti-sgx-ddk-km"
BSP_SGX_DRIVER_VERSION:bsp-gem_o1 = "1.17%"
BSP_SGX_UMLIBS_VERSION:bsp-gem_o1 = "1.17%"
BSP_ROGUE_DRIVER_PROVIDER:bsp-gem_o1 = "ti-img-rogue-driver"
BSP_ROGUE_DRIVER_VERSION:bsp-gem_o1 = "25%"
BSP_ROGUE_UMLIBS_VERSION:bsp-gem_o1 = "25%"
BSP_MESA_PVR_VERSION:bsp-gem_o1 = "24%"
