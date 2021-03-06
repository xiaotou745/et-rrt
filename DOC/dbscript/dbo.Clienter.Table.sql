USE [RenRenDB]
GO
/****** Object:  Table [dbo].[Clienter]    Script Date: 09/25/2015 15:38:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Clienter](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[ClienterName] [nvarchar](50) NOT NULL,
	[PhoneNo] [varchar](20) NOT NULL,
	[Password] [varchar](50) NOT NULL,
	[LoginName] [varchar](50) NOT NULL,
	[HeadImage] [varchar](200) NOT NULL,
	[CityName] [nvarchar](50) NOT NULL,
	[CityCode] [int] NOT NULL,
	[Sex] [smallint] NOT NULL,
	[Age] [int] NOT NULL,
	[Education] [nvarchar](50) NOT NULL,
	[LastLoginTime] [datetime] NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[Status] [int] NOT NULL,
	[LastOptName] [nvarchar](50) NOT NULL,
	[LastOptTime] [datetime] NOT NULL,
 CONSTRAINT [PK_Clienter] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'姓名' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Clienter', @level2type=N'COLUMN',@level2name=N'ClienterName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'手机' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Clienter', @level2type=N'COLUMN',@level2name=N'PhoneNo'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Clienter', @level2type=N'COLUMN',@level2name=N'CityName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'2女默认1男' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Clienter', @level2type=N'COLUMN',@level2name=N'Sex'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'年龄' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Clienter', @level2type=N'COLUMN',@level2name=N'Age'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'学历' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Clienter', @level2type=N'COLUMN',@level2name=N'Education'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'1审核通过,2审核拒绝,默认0待审核' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Clienter', @level2type=N'COLUMN',@level2name=N'Status'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'最后操作人' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Clienter', @level2type=N'COLUMN',@level2name=N'LastOptName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'最后修改时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Clienter', @level2type=N'COLUMN',@level2name=N'LastOptTime'
GO
ALTER TABLE [dbo].[Clienter] ADD  CONSTRAINT [DF_Clienter_PhoneNo]  DEFAULT ('') FOR [PhoneNo]
GO
ALTER TABLE [dbo].[Clienter] ADD  CONSTRAINT [DF_Clienter_HeadImage]  DEFAULT ('') FOR [HeadImage]
GO
ALTER TABLE [dbo].[Clienter] ADD  CONSTRAINT [DF_Clienter_CityName]  DEFAULT ('') FOR [CityName]
GO
ALTER TABLE [dbo].[Clienter] ADD  CONSTRAINT [DF_Clienter_CityCode]  DEFAULT ((0)) FOR [CityCode]
GO
ALTER TABLE [dbo].[Clienter] ADD  CONSTRAINT [DF_Clienter_Sex]  DEFAULT ((1)) FOR [Sex]
GO
ALTER TABLE [dbo].[Clienter] ADD  CONSTRAINT [DF_Clienter_Age]  DEFAULT ((0)) FOR [Age]
GO
ALTER TABLE [dbo].[Clienter] ADD  CONSTRAINT [DF_Clienter_Education]  DEFAULT ('') FOR [Education]
GO
ALTER TABLE [dbo].[Clienter] ADD  CONSTRAINT [DF_Clienter_LastLoginTime]  DEFAULT (getdate()) FOR [LastLoginTime]
GO
ALTER TABLE [dbo].[Clienter] ADD  CONSTRAINT [DF_Clienter_CreateTime]  DEFAULT (getdate()) FOR [CreateTime]
GO
ALTER TABLE [dbo].[Clienter] ADD  CONSTRAINT [DF_Clienter_Status]  DEFAULT ((0)) FOR [Status]
GO
ALTER TABLE [dbo].[Clienter] ADD  CONSTRAINT [DF_Clienter_LastOperatorName]  DEFAULT ('') FOR [LastOptName]
GO
ALTER TABLE [dbo].[Clienter] ADD  CONSTRAINT [DF_Clienter_LastOptTime]  DEFAULT (getdate()) FOR [LastOptTime]
GO
