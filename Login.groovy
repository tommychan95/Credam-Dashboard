import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.builtin.OpenBrowserKeyword as OpenBrowserKeyword
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword as WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable
import io.appium.java_client.ios.options.wda.SupportsSimpleIsVisibleCheckOption
import org.eclipse.persistence.jpa.jpql.AbstractEclipseLinkSemanticValidator
import org.junit.After as After
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testdata.InternalData as InternalData

//=============================================================================================================================

String Email1 = findTestData('credam_data').getValue(1, 1)
String Password1 = findTestData('credam_data').getValue(2, 1)

//=============================================================================================================================

WebUI.setText(findTestObject('Object Repository/Credam_Login/Input_Email'), Email1)
WebUI.setText(findTestObject('Object Repository/Credam_Login/Input_Password'), Password1)

WebUI.click(findTestObject('Object Repository/Credam_Login/View_Password'))
WebUI.click(findTestObject('Object Repository/Credam_Login/Remember_Me'))

WebUI.click(findTestObject('Object Repository/Credam_Login/Prove_not_a_robot'))

WebUI.delay(10)

WebUI.click(findTestObject('Object Repository/Credam_Login/Login_Button'))

//Condition Setting//

def myElement = findTestObject('Object Repository/Credam_Login/Ok-Button')

if (WebUI. waitForElementPresent(myElement, 5, FailureHandling.OPTIONAL))
	
{
 WebUI.click(myElement)
 WebUI.waitForElementVisible(findTestObject('Object Repository/Master_Data_Group/Master_Data_Menu'), 5,
	 FailureHandling.STOP_ON_FAILURE)
}

else {
 WebUI.waitForElementVisible(findTestObject('Object Repository/Master_Data_Group/Master_Data_Menu'), 5, 
	 FailureHandling.STOP_ON_FAILURE)
}
		
