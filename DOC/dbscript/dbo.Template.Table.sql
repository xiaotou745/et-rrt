USE [RenRenDB]
GO
/****** Object:  Table [dbo].[Template]    Script Date: 09/25/2015 15:38:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Template](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[BusinessId] [bigint] NOT NULL,
	[TemplateName] [nvarchar](200) NOT NULL,
	[CreateTime] [datetime] NOT NULL,
	[LastOptName] [nvarchar](50) NOT NULL,
	[LastOptTime] [datetime] NOT NULL,
	[Status] [smallint] NOT NULL,
 CONSTRAINT [PK_Template] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'最后操作人' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Template', @level2type=N'COLUMN',@level2name=N'LastOptName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'最后操作时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Template', @level2type=N'COLUMN',@level2name=N'LastOptTime'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'2禁用 默认1启用' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Template', @level2type=N'COLUMN',@level2name=N'Status'
GO
ALTER TABLE [dbo].[Template] ADD  CONSTRAINT [DF_Template_CreateTime]  DEFAULT (getdate()) FOR [CreateTime]
GO
ALTER TABLE [dbo].[Template] ADD  CONSTRAINT [DF_Template_OptName]  DEFAULT ('') FOR [LastOptName]
GO
ALTER TABLE [dbo].[Template] ADD  CONSTRAINT [DF_Template_LastOptTime]  DEFAULT (getdate()) FOR [LastOptTime]
GO
ALTER TABLE [dbo].[Template] ADD  CONSTRAINT [DF_Template_Status]  DEFAULT ((1)) FOR [Status]
GO
