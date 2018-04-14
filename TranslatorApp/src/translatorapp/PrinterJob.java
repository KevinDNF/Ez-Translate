package translatorapp;

import java.io.File;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import jp.co.kyoceramita.app.AppContext;
import jp.co.kyoceramita.app.attribute.TargetApp;
import jp.co.kyoceramita.attribute.InvalidAttributeException;
import jp.co.kyoceramita.box.document.attribute.TargetRemovableMemoryFolder;
import jp.co.kyoceramita.job.Job;
import jp.co.kyoceramita.job.JobException;
import jp.co.kyoceramita.job.JobService;
import jp.co.kyoceramita.job.JobType;
import jp.co.kyoceramita.job.attribute.AppToPrintJobCreationAttributeSet;
import jp.co.kyoceramita.job.attribute.JobAttributeEventListener;
import jp.co.kyoceramita.job.attribute.JobCreationAttributeSet;
import jp.co.kyoceramita.job.attribute.RemovableMemoryToPrintJobAttributeSet;
import jp.co.kyoceramita.job.attribute.RemovableMemoryToPrintJobCreationAttributeSet;
import jp.co.kyoceramita.job.event.JobEventListener;
import jp.co.kyoceramita.print.attribute.ColorMode;
import jp.co.kyoceramita.storage.StorageType;
import jp.co.kyoceramita.util.KSFUtility;

public class PrinterJob {

	private static PrinterJob printerjob = null;
    private AppToPrintJobCreationAttributeSet att_set = null;
	private Job m_job = null;
	private static int jobStatus;
	private static String sJobCont = "";

	private PrinterJob() {
	}

	public static PrinterJob getInstance() {
		if (printerjob == null)
			createInstance();
		return printerjob;
	}

	private synchronized static void createInstance() {
		if (printerjob == null)
			printerjob = new PrinterJob();
	}

	public void createJobAttributeSet() {

		att_set = (AppToPrintJobCreationAttributeSet) JobCreationAttributeSet.newInstance(JobType.APP_TO_PRINT);
		
		System.out.println("PrinterJob");

	 try {
		 	BundleContext bc = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
		 	AppContext appCtx = KSFUtility.getInstance().getApplicationContext(bc);
         	att_set.set(new TargetApp(appCtx, (new File("").getAbsolutePath()).concat("/TranslatorApp/WebContents/tempPDFs/temp.pdf"), StorageType.HDD));
         	
         		if (att_set.containsCategory(ColorMode.class)) { 
         		System.out.println("Setting color...");
         		att_set.set(ColorMode.AUTO_COLOR);
         		} 
         		// The resolution is set
         		/*if (att_set.containsCategory(ScanResolution.class)) { 
         		att_set.set(ScanResolution.TYPE_300x300); } 
         		// The color setting of the manuscript reading is set full-color. 
         		if (att_set.containsCategory(ScanColorMode.class)) {
         		att_set.set(ScanColorMode.FULL_COLOR); } // The paper size is set to A4. 
         		if (att_set.containsCategory(StoringSize.class)) { 
         		att_set.set(StoringSize.A4); } // The document name is set (The date is added behind the file name). 
         		if (att_set.containsCategory(FileName.class)) { 
         		att_set.set(new FileName("sample", SuffixType.DATE)); } // The file format is set to PDF. 
         		if (att_set.containsCategory(ScanOrgImageType.class)){
         			att_set.set(ScanOrgImageType.OCR);
         		}
         		
         		if (att_set.containsCategory(PDFFileFormat.class)) { 
    				FileImageQuality IQ = FileImageQuality.PDF_IMAGE_QUALITY_1;	
    				
    				PDFFileFormat pdff = new PDFFileFormat(
    					PDFCompatibility.UNAVAILABLE,
    					IQ, new PDFPermission(
    					PDFEditAllowLevel.ANY_EXCEPT_EXTRACTING_PAGES,
    					PDFPrintAllowLevel.ALLOWED, true));
    				    att_set.set(pdff);
         		} 
         		att_set.set(ContinuousScan.ON);*/
        	
        } 
       catch (InvalidAttributeException e) { // handle exceptions. 
        	
        }

		/*JobAttributeEventListener jaev = createJobAttributeEventListener();

		if (jaev == null) {
			System.out.println("Could not create JobAttributeEventListener!");
		}

		att_set.addListener(jaev);*/

	}

	public void createJob() {

		try {
			m_job = JobService.getInstance().create(att_set);
		} catch (JobException e) {
			System.out.println("Job creation failed.");
		}

		// DONE use ScanJobEventListener to get END ( continuous scan case )
		/*ScanJobEventListener sjev = createScanJobEventListener();
		if (sjev == null) {
			System.out.println("Could not create ScanJobEventListener!");
		}

		System.out.println("adding ScanJobEventListener to job.");
		m_job.addListener(sjev);

		JobEventListener jev = createJobEventListener();
		if (jev == null) {
			System.out.println("Could not create JobEventListener!");
		}

		System.out.println("adding JobEventListener to job.");
		m_job.addListener(jev);*/

	}

	public boolean start() {
		
		boolean bRet = false;
		try {
			System.out.println("start called");
			m_job.start();
			bRet = true;
		} catch (JobException e) {
			System.out.println("start failed.");
			bRet = false;
		}
		
		return bRet;
	}

	/*public boolean continuous() {
		
		boolean bRet = false;
		
		try {
			System.out.println("contionous called");
			m_job.continuousScan();
			System.out.println("contionous started");
			bRet =  true;
		} catch (JobException e) {
			System.out.println("start failed.");
			bRet =  false;
		}
		
		return bRet;
	}

	public boolean end() {
		
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
	}

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

	private ScanJobEventListener createScanJobEventListener() {

		System.out.println("creating ScanJobEventListener..");
		ScanJobEventListener listener = new ScanJobEventListener() {

			public void statusChanged(ScanJobEvent event) {
				System.out.println("ScanJobEventListener says : statusChanged(.)");

				ScanJobEventType type = (ScanJobEventType) event.getType();

				if (type.equals(ScanJobEventType.START)) {
					JobID jID = event.getJobID();
					
							System.out.println("ScanJobEventListener says JobEvent: START  jobID = "
									+ jID.getValue());

					jobStatus = 1;
				}

				// Manuscript for platen or ADF scanned
				else if (type.equals(ScanJobEventType.END)) {
					JobID jID = event.getJobID();
					
							System.out.println("ScanJobEventListener says JobEvent: END   jobID = "
									+ jID.getValue());
					jobStatus = 0;
					
					// For continuous scan , do not delete job attributes
					// event.getJobID();
				}
			} // statusChanged
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
	}*/

}

