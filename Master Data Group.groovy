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
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import ch.qos.logback.core.joran.conditional.ElseAction
import groovy.json.StringEscapeUtils
import internal.GlobalVariable
import org.assertj.core.error.ErrorMessageFactory
import org.eclipse.osgi.framework.util.FilePath
import org.eclipse.persistence.jpa.jpql.AbstractEclipseLinkSemanticValidator
import org.openqa.selenium.Keys as Keys
import java.awt.Desktop
import java.io.File
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType

WebUI.click(findTestObject('Object Repository/Master_Data_Group/Master_Data_Menu'))
WebUI.click(findTestObject('Object Repository/Master_Data_Group/Master_Data_Group_Menu'))

def myelement1 = findTestObject('Object Repository/Master_Data_Group/Showing_Pagination')

if (WebUI.waitForElementPresent(myelement1, 5, FailureHandling.OPTIONAL)) {
	
	WebUI.scrollToElement(myelement1, 10, FailureHandling.OPTIONAL)
	WebUI.delay(2)
	//WebUI.scrollToElement(findTestObject('Object Repository/Master_Data_Group/Search_Group_Name'), 5, FailureHandling.OPTIONAL)
}

else {

	WebUI.waitForElementVisible(findTestObject('Object Repository/Master_Data_Group/Search_Group_Name'), 5, 
		FailureHandling.STOP_ON_FAILURE)
	WebUI.waitForElementVisible(findTestObject('Object Repository/Master_Data_Group/Add_Group_Button'), 5, 
		FailureHandling.STOP_ON_FAILURE) 
}

//Audit Trail//
WebUI.click(findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/button_pagination_2'), FailureHandling.STOP_ON_FAILURE)

def auditrail = findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/Audit_Trail_Access_Senayan Group')
def auditraildetails1 = findTestObject("Object Repository/Master_Data_Group/Page_Nobu Credam/View_Audit_Trails_Details_Update_Senayan")
def auditraildetails2 = findTestObject("Object Repository/Master_Data_Group/Page_Nobu Credam/View_Audit_Trails_Details_Create_Senayan")
def auditraildetailsclose = findTestObject("Object Repository/Master_Data_Group/Page_Nobu Credam/Close_Audit_Trails")
def exportauditrail = findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/Export_Audit_Trails')

WebUI.click(auditrail, FailureHandling.STOP_ON_FAILURE)

WebUI.click(auditraildetails1, FailureHandling.STOP_ON_FAILURE)

WebUI.click(auditraildetailsclose, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

WebUI.click(auditraildetails2, FailureHandling.STOP_ON_FAILURE)

WebUI.click(auditraildetailsclose, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

WebUI.click(exportauditrail, FailureHandling.STOP_ON_FAILURE)

//Open Downloaded File//
String FilePath = "C:/User/USER/Downloads/Audit Trail Export - Master Data Group (3)"

// Create File object
File downloadedFile = new File(FilePath)

// Verify file exists
if (downloadedFile.exists()) {
	println "Opening file: " + FilePath
	Desktop.getDesktop().open(downloadedFile)
	WebUI.delay(5)
}

else {
	println "File not found: " + FilePath
	WebUI.delay(2)
}

//Update Group From Details
WebUI.click(findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/button_pagination_2'), 
	FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/a_Kemanggisan'), FailureHandling.
	STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/button_Edit_details'), FailureHandling.
	STOP_ON_FAILURE)

TestObject inputField = findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/input__groupName_details')

String existingText = WebUI.getAttribute(inputField, "value")
String deletedText = "Permai Sentosa"

String updatedText = existingText.replace(deletedText,"Solusindo").trim()

WebUI.setText(inputField, updatedText, FailureHandling.STOP_ON_FAILURE)
WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/Edit_Group_Details'), 
	FailureHandling.STOP_ON_FAILURE)

TestObject obj = findTestObject("Object Repository/Master_Data_Group/Page_Nobu Credam/Group_Information_Kemanggisan_Raya")

String actual = WebUI.getText(obj)

println("Actual text:\n" + actual)

// Check only the needed part
assert actual.contains(updatedText) : "Expected text NOT found: " + updatedText

WebUI.delay(2)

//Delete Group From Details
WebUI.click(findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/a_Credit Loan Agency Pluit'), 
	FailureHandling.STOP_ON_FAILURE)

def deletebtn = findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/button_Delete Group')
WebUI.waitForElementPresent(deletebtn, 5, FailureHandling.STOP_ON_FAILURE)

WebUI.click(deletebtn, FailureHandling.STOP_ON_FAILURE)
WebUI.click(findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/button_Delete_Details'), 
	FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotPresent(findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/a_Credit Loan Agency Pluit')
	, 5, FailureHandling.STOP_ON_FAILURE)

//View Group Details//
WebUI.click(findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/button_pagination_2')
	, FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/a_Gajah Mada'), 
	5, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/a_Gajah Mada'), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Object Repository/Master_Data_Group/Edit_Group_From_Details'), 5, FailureHandling.
	STOP_ON_FAILURE)

WebUI.waitForElementPresent(findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/button_pagination_1_details'), 5, 
	FailureHandling.STOP_ON_FAILURE)

WebUI.scrollToElement(findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/button_pagination_1_details'), 10, 
	FailureHandling.STOP_ON_FAILURE)

WebUI.scrollToElement(findTestObject('Object Repository/Master_Data_Group/Edit_Group_From_Details'), 5, 
	FailureHandling.STOP_ON_FAILURE)

//Check Branch Functions From Group Details//
WebUI.setText(findTestObject('Object Repository/Master_Data_Group/Search_Branch_From_Group_Details'), 'QA', 
	FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

WebUI.clearText(findTestObject('Object Repository/Master_Data_Group/Search_Branch_From_Group_Details'))
WebUI.delay(2)

WebUI.setText(findTestObject('Object Repository/Master_Data_Group/Search_Branch_From_Group_Details'), 'Maker',
	FailureHandling.STOP_ON_FAILURE)

WebUI.clearText(findTestObject('Object Repository/Master_Data_Group/Search_Branch_From_Group_Details'))


//Delete Group//
WebUI.click(findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/Delete_Button_Lippo_Group_3'), 
	FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Master_Data_Group/Delete_Button'), FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotPresent(findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/View_Details_Lippo Group 3'), 5, 
	FailureHandling.STOP_ON_FAILURE)
	
WebUI.delay(2)

//Delete Group With Branch//
WebUI.click(findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/button_pagination_2'), 
	FailureHandling.STOP_ON_FAILURE)

def deletegroup1 = findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/Delete_QA Nobu_Group')
def deletebtn = findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/button_Delete_QA Nobu Group')
def errormessage = findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/You cannot delete data. The data has been_used_Message')
def okbtn = findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/button_Ok_Error_Message')

WebUI.click(deletegroup1, FailureHandling.STOP_ON_FAILURE)
WebUI.click(deletebtn, FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(errormessage, 2, FailureHandling.STOP_ON_FAILURE)
WebUI.click(okbtn, FailureHandling.STOP_ON_FAILURE)

//Delete Group From Details//
def LippoInsuranceGroup = findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/View_Details_Lippo Insurance')
def DeleteLippoInsurance = findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/Delete Group Details')
def DeleteConfirmation = findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/button_Delete_confirmation')

WebUI.click(LippoInsuranceGroup, FailureHandling.STOP_ON_FAILURE)

WebUI.click(DeleteLippoInsurance, FailureHandling.STOP_ON_FAILURE)

WebUI.click(DeleteConfirmation, FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementNotPresent(LippoInsuranceGroup, 2, FailureHandling.OPTIONAL)

WebUI.delay(2)

//Delete Group With Branch From Details//
WebUI.click(findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/button_pagination_2'), FailureHandling.STOP_ON_FAILURE)

def UPHKarawaciGroup = findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/a_UPH Karawaci')
def DeleteKarawaciGroup = findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/Delete Group Details')
def DeleteConfirmation = findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/button_Delete_confirmation')
def ErrorMessage2 = findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/p_You cannot delete data. The data has been_0e0ce5')
def okbtn = findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/button_Ok_Error_Message')

WebUI.click(UPHKarawaciGroup, FailureHandling.STOP_ON_FAILURE)

WebUI.click(DeleteKarawaciGroup, FailureHandling.STOP_ON_FAILURE)

WebUI.click(DeleteConfirmation, FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementPresent(ErrorMessage2, 2, FailureHandling.STOP_ON_FAILURE)

WebUI.click(okbtn, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

//Update Master Group With Empty Group Name//
WebUI.click(findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/Edit_Button_Group_Pocari_Sweat'), 
	FailureHandling.STOP_ON_FAILURE)

WebUI.clearText(findTestObject('Object Repository/Master_Data_Group/Edit_Group_Name'), FailureHandling.STOP_ON_FAILURE)
WebUI.clearText(findTestObject('Object Repository/Master_Data_Group/Edit_Description'), FailureHandling.STOP_ON_FAILURE)

//WebUI.setText(findTestObject('Object Repository/Master_Data_Group/Edit_Group_Name'), 'Lippo Insurance')
WebUI.setText(findTestObject('Object Repository/Master_Data_Group/Edit_Description'), 'Lippo Group 1')

WebUI.click(findTestObject('Object Repository/Master_Data_Group/Edit_Group_Button'))

def errormessage2 = findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/Edit_Group Name must be filled')

WebUI.waitForElementVisible(errormessage2, 5, FailureHandling.STOP_ON_FAILURE)
WebUI.delay(2)


//Search Filter & Add Process//
WebUI.setText(findTestObject('Object Repository/Master_Data_Group/Search_Group_Name'), 'UPH')
WebUI.delay(2)
WebUI.clearText(findTestObject('Object Repository/Master_Data_Group/Search_Group_Name'))
WebUI.delay(2)

WebUI.click(findTestObject('Object Repository/Master_Data_Group/Add_Group_Button'))
WebUI.setText(findTestObject('Object Repository/Master_Data_Group/Input_Group_Name'),'Pocari Sweat')
WebUI.setText(findTestObject('Object Repository/Master_Data_Group/Input_Description'), 'Pocari Sweat Ltd')
WebUI.click(findTestObject('Object Repository/Master_Data_Group/Add_New_Group'))

WebUI.waitForElementPresent(findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/Add_Message_Error The name you entered is already used'), 
	5, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(2)

//Add Branch Via Group
def PocariSweatDetails = findTestObject('Object Repository/Master_Data_Group/Nobu Credam_Add Branch From Group/Page_Nobu Credam/a_Pocari Sweat')
def AddNewBranch = findTestObject('Object Repository/Master_Data_Group/Nobu Credam_Add Branch From Group/button_Add New Branch')
def deleteexistinggroup = findTestObject('Object Repository/Master_Data_Group/Nobu Credam_Add Branch From Group/Page_Nobu Credam/Delete_Pocari Sweat')
def InputBranchName = findTestObject('Object Repository/Master_Data_Group/Nobu Credam_Add Branch From Group/input__branchName')
def InputDescription = findTestObject('Object Repository/Master_Data_Group/Nobu Credam_Add Branch From Group/textarea_Description_description')
def AddConfirmation = findTestObject('Object Repository/Master_Data_Group/Nobu Credam_Add Branch From Group/button_Add New Branch_1')
def DuplicateGroupName = findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/p_Error The name you entered is already in use. Please choose a different name')
def UnselectedGroup = findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/span_Group Name must be selected')
def UnfilledBranchName = findTestObject('Object Repository/Master_Data_Group/Page_Nobu Credam/span_Branch Name must be filled')

WebUI.click(PocariSweatDetails, FailureHandling.STOP_ON_FAILURE)

WebUI.click(AddNewBranch, FailureHandling.STOP_ON_FAILURE)

WebUI.click(deleteexistinggroup, FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Master_Data_Group/Nobu Credam_Add Branch From Group/Select group'))

String optionText = "Pocari Sweat"

// create dynamic xpath
TestObject option = new TestObject("dynamicOption")
option.addProperty("xpath", ConditionType.EQUALS, "//*[contains(text(),'" + optionText + "')]")

// click the option
WebUI.click(option)

WebUI.setText(InputBranchName, 'Pocari Sweat Branch 5')

WebUI.setText(InputDescription, 'Pocari Sweat Branch 5 Jakarta')

WebUI.click(AddConfirmation, FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('Object Repository/Master_Data_Group/Nobu Credam_Add Branch From Group/NoBranch NameRelated GroupDescription'), 5, 
	FailureHandling.OPTIONAL)

WebUI.waitForElementVisible(DuplicateGroupName, 5)

WebUI.waitForElementVisible(UnselectedGroup, 5, FailureHandling.STOP_ON_FAILURE)
WebUI.waitForElementVisible(UnfilledBranchName, 5, FailureHandling.STOP_ON_FAILURE)

String expectedText = "Pocari Sweat Branch 5"

TestObject cell = new TestObject("cell")
cell.addProperty("xpath", ConditionType.EQUALS, "//*[normalize-space(text())='" + expectedText + "']")

WebUI.verifyElementPresent(cell, 10)

WebUI.delay(2)