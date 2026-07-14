# EnderChest

A lightweight and modern multipage Ender Chest plugin for Paper servers.

Unlike the vanilla Ender Chest, EnderChest allows server owners to give players additional storage pages using permissions, making it perfect for survival, RPG, prison, skyblock, and economy servers.

## Features

* Up to 9 Ender Chest pages
* Permission-based page access
* Persistent YAML storage
* Lightweight and fast
* Built for Paper
* Clean and extensible storage architecture
* Open source (GPL-3.0)

If you encounter a bug or have a feature request, please open an issue on [GitHub](https://github.com/IgnisMC/EnderChest/issues).

## Commands

| Command    | Permission    | Description            |
|------------|---------------|------------------------|
| `/storage` | `storage.use` | Opens the storage menu |

## Permissions

| Permission          | Description                | Default |
|---------------------|----------------------------|---------|
| `storage.use`       | Allows use of `/storage`   | `true`  |
| `enderchest.page.1` | Access page 1              | `true`  |
| `enderchest.page.2` | Access page 2              | `op`    |
| `enderchest.page.3` | Access page 3              | `op`    |
| `enderchest.page.4` | Access page 4              | `op`    |
| `enderchest.page.5` | Access page 5              | `op`    |
| `enderchest.page.6` | Access page 6              | `op`    |
| `enderchest.page.7` | Access page 7              | `op`    |
| `enderchest.page.8` | Access page 8              | `op`    |
| `enderchest.page.9` | Access page 9              | `op`    |
| `enderchest.page.*` | Grants access to all pages | `op`    |

## Installation

1. Download the latest release from **[Modrinth](https://modrinth.com/plugin/enderchestig)**.
2. Place the plugin inside your server's `plugins` folder.
3. Restart the server.
4. Configure permissions using your preferred permissions plugin (LuckPerms recommended).

## Storage

Player data is currently stored in YAML files.

Future releases are planned to include additional storage providers such as MySQL.

## License

This project is licensed under the GNU General Public License v3.0 (GPL-3.0).

## Support the Project

If you enjoy this plugin and would like to support future development, you can donate

<a href="https://nowpayments.io/donation/ItzLoghotXD" target="_blank" rel="noreferrer noopener">
   <img style="width: 200px; height: auto;" src="https://nowpayments.io/images/embeds/donation-button-black.svg" alt="Crypto donation button by NOWPayments">
</a>

If you want and if you can then please. It means a lot :D
