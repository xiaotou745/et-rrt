USE [RenRenDB]
GO
/****** Object:  Table [dbo].[PublicProvinceCity]    Script Date: 09/25/2015 15:38:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PublicProvinceCity](
	[ID] [int] NOT NULL,
	[Name] [nvarchar](150) NULL,
	[Code] [int] NULL,
	[ParentCode] [int] NULL,
	[Jibie] [int] NULL,
	[LowerAcronym] [nvarchar](50) NULL,
	[UpperAcronym] [nvarchar](50) NULL,
	[LowerFullPinyin] [nvarchar](500) NULL
) ON [PRIMARY]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'城市/区域名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PublicProvinceCity', @level2type=N'COLUMN',@level2name=N'Name'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'当前城市/区域Code' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PublicProvinceCity', @level2type=N'COLUMN',@level2name=N'Code'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'父级Code' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PublicProvinceCity', @level2type=N'COLUMN',@level2name=N'ParentCode'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'级别' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PublicProvinceCity', @level2type=N'COLUMN',@level2name=N'Jibie'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'小写拼音缩写' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PublicProvinceCity', @level2type=N'COLUMN',@level2name=N'LowerAcronym'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'大写拼音缩写' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PublicProvinceCity', @level2type=N'COLUMN',@level2name=N'UpperAcronym'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'小写全拼' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PublicProvinceCity', @level2type=N'COLUMN',@level2name=N'LowerFullPinyin'
GO
