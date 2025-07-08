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
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.callTestCase(findTestCase('Blocks/Reusable_TC/OpenBrowser'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Object Repository/Header/HeaderWithoutLogin/btnCart'))

if(WebUI.waitForElementVisible(findTestObject('Object Repository/Cart/tableTitleImage'), 5)) {
	
	WebUI.click(findTestObject('Object Repository/Cart/btnClearcart'))
	
}

WebUI.click(findTestObject('Object Repository/Header/titleBookCart'))

WebUI.click(findTestObject('Object Repository/Home/btnAddtoCart'))

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Header/HeaderWithoutLogin/numberOfCarts')), '1', false)

WebUI.waitForElementVisible(findTestObject('Object Repository/Home/bookTitle'), 10)

WebUI.click(findTestObject('Object Repository/Header/HeaderWithoutLogin/btnCart'))

WebUI.click(findTestObject('Object Repository/Cart/btnClearcart'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Cart/titleShoppingcart'), 10)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Cart/titleShoppingcart')), 'Shopping cart', false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Cart/emptyMessage')), 'Your shopping cart is empty.', 
    false)

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Cart/btnContinueshopping')), 'Continue shopping', false)

WebUI.click(findTestObject('Object Repository/Cart/btnContinueshopping'))

WebUI.waitForElementVisible(findTestObject('Object Repository/Home/bookTitle'), 10)

WebUI.click(findTestObject('Object Repository/Home/btnAddtoCart'))

WebUI.refresh()

WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Header/HeaderWithoutLogin/numberOfCarts')), '0', false)

WebUI.callTestCase(findTestCase('Blocks/Reusable_TC/CloseBrowser'), [:], FailureHandling.STOP_ON_FAILURE)

