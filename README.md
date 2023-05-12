# NoMassScan
一个阻止外挂客户端通过TAB_COMPLETE包获取插件列表的插件,需要ProtocolLib  
此插件有针对Meteor Client做优化,使用 Meteor Client 命令 ".server plugins MassScan" 会被踢出服务器  
注意: 如需完全防御插件列表被获取,你还需要像是 [CommandBlocker](https://forums.papermc.io/threads/46/) 这种插件用于阻止其他的办法用于获取插件列表