USE [RenRenDB]
GO
/****** Object:  Table [dbo].[RoleInfo]    Script Date: 09/25/2015 15:38:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RoleInfo](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[RoleName] [varchar](50) NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[Remark] [nvarchar](500) NOT NULL,
	[OptName] [nvarchar](150) NOT NULL,
	[BeLock] [bit] NOT NULL
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'角色名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'RoleInfo', @level2type=N'COLUMN',@level2name=N'RoleName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'是否可用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'RoleInfo', @level2type=N'COLUMN',@level2name=N'BeLock'
GO
ALTER TABLE [dbo].[RoleInfo] ADD  CONSTRAINT [DF_RoleInfo_RoleName]  DEFAULT ('') FOR [RoleName]
GO
ALTER TABLE [dbo].[RoleInfo] ADD  CONSTRAINT [DF_RoleInfo_CreateTime]  DEFAULT (getdate()) FOR [CreateTime]
GO
ALTER TABLE [dbo].[RoleInfo] ADD  CONSTRAINT [DF_RoleInfo_Remark]  DEFAULT ('') FOR [Remark]
GO
ALTER TABLE [dbo].[RoleInfo] ADD  CONSTRAINT [DF_RoleInfo_OptName]  DEFAULT ('') FOR [OptName]
GO
ALTER TABLE [dbo].[RoleInfo] ADD  CONSTRAINT [DF_RoleInfo_BeLock]  DEFAULT ((0)) FOR [BeLock]
GO
