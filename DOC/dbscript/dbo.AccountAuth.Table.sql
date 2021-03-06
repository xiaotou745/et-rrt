USE [RenRenDB]
GO
/****** Object:  Table [dbo].[AccountAuth]    Script Date: 09/25/2015 15:38:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AccountAuth](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[AccoutId] [int] NOT NULL,
	[MenuId] [int] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[OptName] [nvarchar](150) NOT NULL
) ON [PRIMARY]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'账号id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountAuth', @level2type=N'COLUMN',@level2name=N'AccoutId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'菜单id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'AccountAuth', @level2type=N'COLUMN',@level2name=N'MenuId'
GO
ALTER TABLE [dbo].[AccountAuth] ADD  CONSTRAINT [DF_AccountAuth_CreateTime]  DEFAULT (getdate()) FOR [CreateTime]
GO
ALTER TABLE [dbo].[AccountAuth] ADD  CONSTRAINT [DF_AccountAuth_OptName]  DEFAULT ('') FOR [OptName]
GO
