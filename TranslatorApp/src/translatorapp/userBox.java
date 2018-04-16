package translatorapp;

import java.util.List;

import jp.co.kyoceramita.box.BoxException;
import jp.co.kyoceramita.box.UserBox;
import jp.co.kyoceramita.box.UserBoxDetailInfo;
import jp.co.kyoceramita.box.UserBoxService;
import jp.co.kyoceramita.box.attribute.DeleteSetting;

public class userBox {

	public userBox(){
		UserBoxService ubs = UserBoxService.getInstance();
		
		UserBox tempStorage = ubs.newUserBox();
		
		tempStorage.setDetailInfo((UserBoxDetailInfo) DeleteSetting.OFF);//not sure if this is right
	}	
		
}
