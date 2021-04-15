package com.org.data_provider;

import java.net.URL;
import com.org.util.Language;
import org.testng.annotations.DataProvider;
public class LoginDataProvider {

    @DataProvider(name="LoginDataProvider")
    public static Object[][] getDataFromDataprovider(){    
    	URL url=LoginDataProvider.class.getResource("/test_data/Login_"+Language.language+".xlsx");
        return DataProviderUtil.getData(url.getPath());      
    }

}
