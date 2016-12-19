CREATE TABLE [email_contact] (
    [id] int IDENTITY(1,1) NOT NULL,
    [emailAddress] [varchar](254) NOT NULL,
    [recipientType] [varchar](3) NOT NULL
 CONSTRAINT [PK_EMAILCONTACT] PRIMARY KEY CLUSTERED 
(
    [id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

INSERT email_contact (emailAddress, recipientType) VALUES ('Pavel.Gasparik@muehlbauer.sk', 'TO')
INSERT email_contact (emailAddress, recipientType) VALUES ('jozef.macho@muehlbauer.sk', 'CC')