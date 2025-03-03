#!/bin/sh

firstboot_enabled()
{
    if [ -z "$bootparam_bootpart" ] || [ "$bootparam_firstboot" != 1 ]; then
        return 1
    fi

    printf "\n\n" >/dev/console
    msg "Looks like system is booting for the first time"

    return 0
}

get_disk_of_partition()
{
    part_name="$(basename "$1")"
    sysfs_part_path="$(readlink -f "/sys/class/block/$part_name")"
    sysfs_disk_path="$(dirname "$sysfs_part_path")"

    if [ ! -d "$sysfs_disk_path" ]; then
        return 1
    fi

    printf "%s" "/dev/$(basename "$sysfs_disk_path")"
    return 0
}

firstboot_run()
{
    boot_mntpoint="/boot"

    mkdir -p "$boot_mntpoint"

    if ! mount "$bootparam_bootpart" "$boot_mntpoint"; then
        msg "Can't mount boot partition"
        return 1
    fi

    if ! disk_dev=$(get_disk_of_partition "$bootparam_bootpart"); then
        msg "Unable to get disk that partition belongs to"
        return 1 
    fi

    msg "Expanding root partition on '${disk_dev}'..."

    if ! parted --script "$disk_dev" -- resizepart 2 -1 > /dev/null; then
        msg "Couldn't resize root partition"
        return
    fi

    tmp_root_dir="/tmp/rootfs"

    mkdir -p "$tmp_root_dir"

    if ! mount "${bootparam_bootpart%?}2" "$tmp_root_dir"; then
        msg "Unable to mount root partition"
        return
    fi

    if ! btrfs filesystem resize max "$tmp_root_dir"; then
        msg "Unable to expand root filesystem"
        return
    fi

    sed -i 's/firstboot=1/firstboot=0/' "$boot_mntpoint/uEnv.txt"
    msg "Operation is successful."

    umount "$boot_mntpoint" "$tmp_root_dir"

    printf "\n\n" >/dev/console
}
