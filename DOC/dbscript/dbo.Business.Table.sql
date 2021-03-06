USE [RenRenDB]
GO
/****** Object:  Table [dbo].[Business]    Script Date: 09/25/2015 15:38:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Business](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[CompanyName] [nvarchar](250) NOT NULL,
	[PhoneNo] [varchar](20) NOT NULL,
	[PassWord] [varchar](50) NOT NULL,
	[LoginName] [varchar](50) NOT NULL,
	[Logo] [nvarchar](255) NOT NULL,
	[Address] [varchar](255) NOT NULL,
	[CityCode] [int] NOT NULL,
	[CityName] [nvarchar](50) NOT NULL,
	[WebSite] [varchar](255) NOT NULL,
 CONSTRAINT [PK_Business] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'密码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Business', @level2type=N'COLUMN',@level2name=N'PassWord'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'登录名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Business', @level2type=N'COLUMN',@level2name=N'LoginName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'公司注册地址' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Business', @level2type=N'COLUMN',@level2name=N'Address'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'城市编码' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Business', @level2type=N'COLUMN',@level2name=N'CityCode'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'城市名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Business', @level2type=N'COLUMN',@level2name=N'CityName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'公司网址' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Business', @level2type=N'COLUMN',@level2name=N'WebSite'
GO
ALTER TABLE [dbo].[Business] ADD  CONSTRAINT [DF_Business_TrueName]  DEFAULT ('') FOR [CompanyName]
GO
ALTER TABLE [dbo].[Business] ADD  CONSTRAINT [DF_Business_PhoneNo]  DEFAULT ('') FOR [PhoneNo]
GO
ALTER TABLE [dbo].[Business] ADD  CONSTRAINT [DF_Business_PassWord]  DEFAULT ('') FOR [PassWord]
GO
ALTER TABLE [dbo].[Business] ADD  CONSTRAINT [DF_Business_LoginName]  DEFAULT ('') FOR [LoginName]
GO
ALTER TABLE [dbo].[Business] ADD  CONSTRAINT [DF_Business_Logo]  DEFAULT ('') FOR [Logo]
GO
ALTER TABLE [dbo].[Business] ADD  CONSTRAINT [DF_Business_Address]  DEFAULT ('') FOR [Address]
GO
ALTER TABLE [dbo].[Business] ADD  CONSTRAINT [DF_Business_CityCode]  DEFAULT ((0)) FOR [CityCode]
GO
ALTER TABLE [dbo].[Business] ADD  CONSTRAINT [DF_Business_CityName]  DEFAULT ('') FOR [CityName]
GO
ALTER TABLE [dbo].[Business] ADD  CONSTRAINT [DF_Business_WebSite]  DEFAULT ('') FOR [WebSite]
GO
