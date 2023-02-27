module home.smart.minecraft_plugin.minecraft_adapter {
    requires transitive home.smart.minecraft_plugin.controller;
    exports home.smart.minecraft_plugin.minecraft_adapter.api.call;
    exports home.smart.minecraft_plugin.minecraft_adapter.api.implement;
    exports home.smart.minecraft_plugin.minecraft_adapter.model;
    exports home.smart.minecraft_plugin.minecraft_adapter.start;
}