USE [RenRenDB]
GO
/****** Object:  Table [dbo].[RoleAuth]    Script Date: 09/25/2015 15:38:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[RoleAuth](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[RoleId] [int] NOT NULL,
	[MenuId] [int] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[OptName] [nvarchar](150) NOT NULL
) ON [PRIMARY]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'角色id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'RoleAuth', @level2type=N'COLUMN',@level2name=N'RoleId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'菜单id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'RoleAuth', @level2type=N'COLUMN',@level2name=N'MenuId'
GO
ALTER TABLE [dbo].[RoleAuth] ADD  CONSTRAINT [DF_RoleAuth_RoleId]  DEFAULT ((0)) FOR [RoleId]
GO
ALTER TABLE [dbo].[RoleAuth] ADD  CONSTRAINT [DF_RoleAuth_MenuId]  DEFAULT ((0)) FOR [MenuId]
GO
ALTER TABLE [dbo].[RoleAuth] ADD  CONSTRAINT [DF_RoleAuth_CreateTime]  DEFAULT (getdate()) FOR [CreateTime]
GO
ALTER TABLE [dbo].[RoleAuth] ADD  CONSTRAINT [DF_RoleAuth_OptName]  DEFAULT ('') FOR [OptName]
GO
