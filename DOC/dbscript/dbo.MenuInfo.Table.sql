USE [RenRenDB]
GO
/****** Object:  Table [dbo].[MenuInfo]    Script Date: 09/25/2015 15:38:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[MenuInfo](
	[Id] [int] NOT NULL,
	[ParId] [int] NOT NULL,
	[MenuName] [varchar](50) NOT NULL,
	[BeLock] [bit] NOT NULL,
	[Url] [varchar](500) NOT NULL,
	[IsButton] [bit] NOT NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'父id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MenuInfo', @level2type=N'COLUMN',@level2name=N'ParId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'菜单名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MenuInfo', @level2type=N'COLUMN',@level2name=N'MenuName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否可用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MenuInfo', @level2type=N'COLUMN',@level2name=N'BeLock'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'菜单地址' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MenuInfo', @level2type=N'COLUMN',@level2name=N'Url'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否按钮' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MenuInfo', @level2type=N'COLUMN',@level2name=N'IsButton'
GO
ALTER TABLE [dbo].[MenuInfo] ADD  CONSTRAINT [DF_Menu_ParId]  DEFAULT ((0)) FOR [ParId]
GO
ALTER TABLE [dbo].[MenuInfo] ADD  CONSTRAINT [DF_Menu_MenuName]  DEFAULT ('') FOR [MenuName]
GO
ALTER TABLE [dbo].[MenuInfo] ADD  CONSTRAINT [DF_Menu_BeLock]  DEFAULT ((0)) FOR [BeLock]
GO
ALTER TABLE [dbo].[MenuInfo] ADD  CONSTRAINT [DF_Menu_Url]  DEFAULT ('') FOR [Url]
GO
