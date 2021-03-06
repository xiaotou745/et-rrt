USE [RenRenDB]
GO
/****** Object:  Table [dbo].[Attachment]    Script Date: 09/25/2015 15:38:40 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Attachment](
	[Id] [bigint] IDENTITY(1,1) NOT NULL,
	[BusinessId] [bigint] NOT NULL,
	[TaskId] [bigint] NOT NULL,
	[AttachmentName] [nvarchar](500) NOT NULL,
	[AttachUrl] [nvarchar](500) NOT NULL,
	[UploadTime] [datetime] NOT NULL,
 CONSTRAINT [PK_Attachment] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'主键' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Attachment', @level2type=N'COLUMN',@level2name=N'Id'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'商家Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Attachment', @level2type=N'COLUMN',@level2name=N'BusinessId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'任务Id' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Attachment', @level2type=N'COLUMN',@level2name=N'TaskId'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'附件名称' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Attachment', @level2type=N'COLUMN',@level2name=N'AttachmentName'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'附件地址' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Attachment', @level2type=N'COLUMN',@level2name=N'AttachUrl'
GO
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'上传时间' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Attachment', @level2type=N'COLUMN',@level2name=N'UploadTime'
GO
ALTER TABLE [dbo].[Attachment] ADD  CONSTRAINT [DF_Attachment_AttachmentName]  DEFAULT ('') FOR [AttachmentName]
GO
ALTER TABLE [dbo].[Attachment] ADD  CONSTRAINT [DF_Attachment_AttachUrl]  DEFAULT ('') FOR [AttachUrl]
GO
ALTER TABLE [dbo].[Attachment] ADD  CONSTRAINT [DF_Attachment_CreateTime]  DEFAULT (getdate()) FOR [UploadTime]
GO
