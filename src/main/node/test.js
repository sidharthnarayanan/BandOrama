import { Builder, By, Key, until } from 'selenium-webdriver';
var timeBtwnCommands = 1000; //1sec

async function testLogin() {
  let driver = await new Builder().forBrowser('chrome').build();
  try {
    await driver.get('http://127.0.0.1:3000/Login.html');
    await new Promise(resolve => setTimeout(resolve, timeBtwnCommands));
    await driver.findElement(By.id('uName')).sendKeys('sid', Key.RETURN);
    await new Promise(resolve => setTimeout(resolve, timeBtwnCommands));
    await driver.findElement(By.id('pswd')).sendKeys('god', Key.RETURN);
    await new Promise(resolve => setTimeout(resolve, timeBtwnCommands));
    await driver.findElement(By.id('gobtn')).click();
    await new Promise(resolve => setTimeout(resolve, timeBtwnCommands));
    await driver.wait(until.titleIs('Dashboard'), 5000);
    await new Promise(resolve => setTimeout(resolve, timeBtwnCommands));
  } finally {
    await driver.quit();
  }
}

testLogin();