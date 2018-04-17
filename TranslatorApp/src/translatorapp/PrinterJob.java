package translatorapp;

import java.io.File;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import jp.co.kyoceramita.app.AppContext;
import jp.co.kyoceramita.app.attribute.TargetApp;
import jp.co.kyoceramita.attribute.InvalidAttributeException;
import jp.co.kyoceramita.box.document.attribute.TargetRemovableMemoryFile;
import jp.co.kyoceramita.box.document.attribute.TargetRemovableMemoryFolder;
import jp.co.kyoceramita.job.Job;
import jp.co.kyoceramita.job.JobException;
import jp.co.kyoceramita.job.JobService;
import jp.co.kyoceramita.job.JobType;
import jp.co.kyoceramita.job.attribute.AppToPrintJobCreationAttributeSet;
import jp.co.kyoceramita.job.attribute.JobAttributeEvent;
import jp.co.kyoceramita.job.attribute.JobAttributeEventListener;
import jp.co.kyoceramita.job.attribute.JobAttributeEventType;
import jp.co.kyoceramita.job.attribute.JobCreationAttributeSet;
import jp.co.kyoceramita.job.attribute.JobID;
import jp.co.kyoceramita.job.attribute.MediaSizeName;
import jp.co.kyoceramita.job.attribute.Message;
import jp.co.kyoceramita.job.attribute.MessageID;
import jp.co.kyoceramita.job.attribute.Orientation;
import jp.co.kyoceramita.job.attribute.RemovableMemoryToPrintJobAttributeSet;
import jp.co.kyoceramita.job.attribute.RemovableMemoryToPrintJobCreationAttributeSet;
import jp.co.kyoceramita.job.event.JobEvent;
import jp.co.kyoceramita.job.event.JobEventListener;
import jp.co.kyoceramita.job.event.JobEventType;
import jp.co.kyoceramita.message.screen.BrowserMessageSender;
import jp.co.kyoceramita.print.attribute.ColorMode;
import jp.co.kyoceramita.print.attribute.Copies;
import jp.co.kyoceramita.print.attribute.DeleteAfterPrinted;
import jp.co.kyoceramita.print.attribute.Duplex;
import jp.co.kyoceramita.print.attribute.Ecoprint;
import jp.co.kyoceramita.print.attribute.MPTraySetting;
import jp.co.kyoceramita.print.attribute.PaperSelection;
import jp.co.kyoceramita.storage.StorageManager;
import jp.co.kyoceramita.storage.StorageType;
import jp.co.kyoceramita.util.KSFUtility;

public class PrinterJob {

    private RemovableMemoryToPrintJobCreationAttributeSet att_set = null;
	private Job m_job = null;
//---
	private static int jobStatus;
	private static String sJobCont = "";
	private static PrinterJob printerjob = null;
//---

	private AppContext appCtx;
	
	public PrinterJob(BundleContext bc) {
		
		KSFUtility ksf = KSFUtility.getInstance(); 
		//can be simplified out of this class
		appCtx = ksf.getApplicationContext(bc);
	}
	
	//Sets the attributes for the printer job
	public void createJobAttributeSet() {

		att_set = (RemovableMemoryToPrintJobCreationAttributeSet) JobCreationAttributeSet.newInstance(JobType.REMOVABLE_MEMORY_TO_PRINT);
		
		System.out.println("PrinterJob creating attribute set");

		
		
	 try {
		 	    att_set.set(new TargetRemovableMemoryFolder(".temp"));
		 	    
		 	    att_set.set(new TargetRemovableMemoryFile(".temppdf.pdf"));
         	
         		if (att_set.containsCategory(ColorMode.class)) { 
         		System.out.println("Setting color...");
         		att_set.set(ColorMode.AUTO_COLOR);
         		}
         		
         		if (att_set.containsCategory(Copies.class)) { 
             		System.out.println("Setting copies...");
             		att_set.set(new Copies(1));
             	}
         		
         		if (att_set.containsCategory(DeleteAfterPrinted.class)) { 
             		System.out.println("Setting deleteAfterPrinted...");
             		att_set.set(DeleteAfterPrinted.OFF);
             	}
         		
         		if (att_set.containsCategory(Duplex.class)) { 
             		System.out.println("Setting duplex...");
             		att_set.set(Duplex.ONE_SIDED);
             	}
         		
         		if (att_set.containsCategory(Ecoprint.class)) { 
             		System.out.println("Setting ecoprint...");
             		att_set.set(Ecoprint.OFF);
             	}
         		
         		if (att_set.containsCategory(MPTraySetting.class)) { 
             		System.out.println("Setting mpTraySetting...");
             		att_set.set(new MPTraySetting(MediaSizeName.ISO_A4, Orientation.E, false));
             	}
         		
         		if (att_set.containsCategory(PaperSelection.class)) { 
             		System.out.println("Setting paperSelection...");
             		att_set.set(PaperSelection.AUTO);
             	}
        	
        } 
       catch (InvalidAttributeException e) { // handle exceptions. 
        	
        }

		JobAttributeEventListener jaev = createJobAttributeEventListener();

		if (jaev == null) {
			System.out.println("Could not create JobAttributeEventListener!");
		}

		System.out.println("adding JobAttributeEventListener to jobAttributes.");
		att_set.addListener(jaev);

	}
	

	//Create job move out of here
	public void createJob() {

		try {
			m_job = JobService.getInstance().create(att_set);
		} catch (JobException e) {
			System.out.println("Job creation failed.");
		}

		// DONE use ScanJobEventListener to get END ( continuous scan case )
		

		JobEventListener jev = createJobEventListener();
		if (jev == null) {
			System.out.println("Could not create JobEventListener!");
		}

		System.out.println("adding JobEventListener to job.");
		m_job.addListener(jev);
		System.out.println("JobEventListener created.");

	}
	
	//Start job Move out of here
	public boolean start() {
		
		boolean bRet = false;
		try {
			System.out.println("start called");
			m_job.start();
			try {
				System.out.println("Now it should be printing from folder: " + m_job.getJobAttributes().get(TargetRemovableMemoryFolder.class));
				System.out.println("Now it should be printing file: " + m_job.getJobAttributes().get(TargetRemovableMemoryFile.class));
			} catch (InvalidAttributeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bRet = true;
		} catch (JobException e) {
			System.out.println("start failed.");
			bRet = false;
		}
		
		return bRet;
	}

	/*public boolean end() {
		
		boolean bRet = false;
		try {
			att_set.set(ContinuousScan.OFF);
			m_job.continuousScanEnd();
			bRet =  true;
			} catch (InvalidAttributeException e) {

			bRet=false;
		}catch (JobException e) {
			System.out.println("start failed.");
			bRet = false;
		}
		
		return bRet;
	}*/

	private JobAttributeEventListener createJobAttributeEventListener() {

		System.out.println("creating JobAttributeEventListener..");
		JobAttributeEventListener listener = new JobAttributeEventListener() {

			public void attributeUpdate(JobAttributeEvent event) {
				
						System.out.println("JobAttributeEventListener says: attributeUpdate(.)");

				JobCreationAttributeSet jcas = (JobCreationAttributeSet) event
						.getJobAttributeSet();

				JobAttributeEventType type = (JobAttributeEventType) event
						.getType();
				// The start rule is generated.
				if (type.equals(JobAttributeEventType.START_PROHIBITION)) {
					
							System.out.println("JobAttributeEventType is : START_PROHIBITION");
				}
				// The combination rule is generated.
				else if (type.equals(JobAttributeEventType.COMB_CONSTRAINT)) {
					System.out.println("JobAttributeEventType is : COMB_CONSTRAINT");
				}
				// The change in the job setting by the rule is generated.
				else if (type.equals(JobAttributeEventType.CHANGE_JOB_SETTINGS)) {
					
							System.out.println("JobAttributeEventType is : CHANGE_JOB_SETTINGS");
				}
				// The change of the display message of the panel by the job
				// setting is generated.
				else if (type.equals(JobAttributeEventType.CHANGE_MAIN_MESSAGE)) {
					
							System.out.println("JobAttributeEventType is : CHANGE_MAIN_MESSAGE");
				}

			}
		};

		return listener;
	}

	private JobEventListener createJobEventListener() {

		System.out.println("creating JobEventListener..");
		JobEventListener listener = new JobEventListener() {

			public void statusChanged(JobEvent event) {
				System.out.println("JobEventListener says : statusChanged(.)");
				JobEventType type = (JobEventType) event.getType();

				JobID jID = event.getJobID();

				System.out.println("JobEventListener jID is : " + jID.getValue());

				if (type.equals(JobEventType.START)) {
					System.out.println("JobEventListener says JobEvent: START");
					jobStatus = 1;
				}
				// Job completion
				else if (type.equals(JobEventType.COMPLETE)) {
					System.out.println("JobEventListener says JobEvent: COMPLETE");
					jobStatus = 0;
					// TBD
					deleteJobAttributeSet();

					sJobCont = ""; // No need
				}
				// Job cancellation
				else if (type.equals(JobEventType.CANCEL)) {
					System.out.println("JobEventListener says JobEvent: CANCEL");
					jobStatus = 0;
					deleteJobAttributeSet();
				} else if (type.equals(JobEventType.ABORT)) {
					System.out.println("JobEventListener says JobEvent: ABORT");
					deleteJobAttributeSet();
				} else if (type.equals(JobEventType.RESERVE)) {
					System.out.println("JobEventListener says JobEvent: RESERVE");
				} else if (type.equals(JobEventType.RESUME)) {
					System.out.println("JobEventListener says JobEvent: RESUME");
				} else if (type.equals(JobEventType.SUSPEND)) {
					System.out.println("JobEventListener says JobEvent: SUSPEND");
				} else if (type.equals(JobEventType.UNDEFINED)) {
					System.out.println("JobEventListener says JobEvent: UNDEFINED");
				} else if (type.equals(JobEventType.UPDATE)) {
					System.out.println("JobEventListener says JobEvent: UPDATE");
				} else if (type.equals(JobEventType.WAIT)) {
					System.out.println("JobEventListener says JobEvent: WAIT");
				}
			}
		};

		return listener;

	}

	private void dumpMessages(JobCreationAttributeSet jcas) {
		Message[] msg = jcas.getMainMessages();
		System.out.println("JobCreationAttributeSet has : " + msg.length
				+ " messages.");
		for (int i = 0; i < msg.length; i++) {
			MessageID msgID = msg[i].getMessageID();
			String strMsg = msg[i].getString();
			String strMsgID = msgID.toString();
			System.out.println("strMsgID: " + strMsgID + "strMsg: " + strMsg);
		}
	}

	public void deleteJobAttributeSet() {
		if (att_set != null) {
			att_set.delete();
		}
		
		// 28 important
		att_set = null;
		System.out.println("JobCreationAttributeSet deleted.");
		jobStatus = 0;
		System.out.println("jobStatus set to: 0");
	}

	private void sendMessage(int iEvent) {

		int eventNo = iEvent;
		BrowserMessageSender ms = BrowserMessageSender.getInstance();
		//ms.send(HyPASActivator.getAppContext(), eventNo);
	}

}

