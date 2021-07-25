[Ivy]
17AD4578E1FC31F7 9.2.0 #module
>Proto >Proto Collection #zClass
Us0 UserStatusButtonProcess Big #zClass
Us0 RD #cInfo
Us0 #process
Us0 @AnnotationInP-0n ai ai #zField
Us0 @TextInP .type .type #zField
Us0 @TextInP .processKind .processKind #zField
Us0 @TextInP .xml .xml #zField
Us0 @TextInP .responsibility .responsibility #zField
Us0 @UdInit f0 '' #zField
Us0 @UdProcessEnd f1 '' #zField
Us0 @PushWFArc f2 '' #zField
Us0 @UdEvent f3 '' #zField
Us0 @UdExitEnd f4 '' #zField
Us0 @PushWFArc f5 '' #zField
>Proto Us0 Us0 UserStatusButtonProcess #zField
Us0 f0 guid 17AD4578E58A798D #txt
Us0 f0 method start() #txt
Us0 f0 inParameterDecl '<> param;' #txt
Us0 f0 outParameterDecl '<> result;' #txt
Us0 f0 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>start()</name>
    </language>
</elementInfo>
' #txt
Us0 f0 83 51 26 26 -16 15 #rect
Us0 f1 211 51 26 26 0 12 #rect
Us0 f2 109 64 211 64 #arcP
Us0 f3 guid 17AD4578E65C637F #txt
Us0 f3 actionTable 'out=in;
' #txt
Us0 f3 @C|.xml '<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<elementInfo>
    <language>
        <name>close</name>
    </language>
</elementInfo>
' #txt
Us0 f3 83 147 26 26 -14 15 #rect
Us0 f4 211 147 26 26 0 12 #rect
Us0 f5 109 160 211 160 #arcP
>Proto Us0 .type com.axonivy.userstatus.component.UserStatusButton.UserStatusButtonData #txt
>Proto Us0 .processKind HTML_DIALOG #txt
>Proto Us0 -8 -8 16 16 16 26 #rect
Us0 f0 mainOut f2 tail #connect
Us0 f2 head f1 mainIn #connect
Us0 f3 mainOut f5 tail #connect
Us0 f5 head f4 mainIn #connect
