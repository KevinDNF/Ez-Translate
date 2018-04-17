package translatorapp;

import java.util.List;

import jp.co.kyoceramita.box.BoxException;
import jp.co.kyoceramita.box.InvalidPasswordException;
import jp.co.kyoceramita.box.UserBox;
import jp.co.kyoceramita.box.UserBoxDetailInfo;
import jp.co.kyoceramita.box.UserBoxService;
import jp.co.kyoceramita.box.attribute.DeleteSetting;
import jp.co.kyoceramita.box.attribute.OverwriteSetting;

public class userBox {

	public userBox(){
		UserBoxService ubs = UserBoxService.getInstance();
		
		UserBox tempStorage = ubs.newUserBox();
		
		UserBoxDetailInfo detailInfo = null;
		try {
			detailInfo = tempStorage.getDetailInfo("");
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not get the detail info about userbox");
			e.printStackTrace();
		} catch (BoxException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not get the detail info about userbox");
			e.printStackTrace();
		}
		
		detailInfo.setMaxSize(10);
		//detailInfo.setStoragePeriod(0);
		detailInfo.setDeleteSetting(DeleteSetting.OFF);
		tempStorage.setDetailInfo(detailInfo);
		
		try {
			detailInfo = tempStorage.getDetailInfo("");
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not get the detail info about userbox");
			e.printStackTrace();
		} catch (BoxException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not get the detail info about userbox");
			e.printStackTrace();
		}
		
		System.out.println(tempStorage.getAvailableSpace());
		System.out.println(detailInfo.getMaxSize());
		System.out.println(detailInfo.getStoragePeriod());
		System.out.println(detailInfo.getDeleteSetting());
		System.out.println(detailInfo.getOverwriteSetting());
		try {
			System.out.println(tempStorage.getDocumentList("").toString());
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BoxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(tempStorage.exists());
		
		//tempStorage.setDetailInfo();//not sure if this is right
		
	}	
		
}
