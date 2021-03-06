USE [RenRenDB]
GO
/****** Object:  Table [dbo].[BusinessBalance]    Script Date: 09/25/2015 15:38:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BusinessBalance](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[BusinessId] [bigint] NOT NULL,
	[Balance] [decimal](18, 2) NOT NULL,
 CONSTRAINT [PK_BusinessBalance] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'商家ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BusinessBalance', @level2type=N'COLUMN',@level2name=N'BusinessId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'余额' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'BusinessBalance', @level2type=N'COLUMN',@level2name=N'Balance'
GO
ALTER TABLE [dbo].[BusinessBalance] ADD  CONSTRAINT [DF_BusinessBalance_Balance]  DEFAULT ((0)) FOR [Balance]
GO
