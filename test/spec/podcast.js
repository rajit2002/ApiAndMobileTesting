describe('Podcast Player Automation', () => {

  it('Tap, scroll, send keys', async () => {
    // Allow time for the main activity to load completely
    await driver.pause(20000); // Adjust pause time as necessary

    // Click on the podcast navigation icon using XPath
    const podcastElement = await $('(//android.widget.ImageView[@resource-id="au.com.shiftyjelly.pocketcasts:id/navigation_bar_item_icon_view"])[1]');
    await podcastElement.click();

    // Click on the search icon using Accessibility ID
    const searchBar = await $('~Search podcasts');
    await searchBar.click();

    // Enter the search term in the search input box using XPath
    const searchInputBox = await $('//android.widget.EditText[@resource-id="au.com.shiftyjelly.pocketcasts:id/search_src_text"]');
    await searchInputBox.setValue('The Daily');

    // Locate and click the podcast item using XPath
    const podcast = await $('//androidx.compose.ui.platform.ComposeView[@resource-id="au.com.shiftyjelly.pocketcasts:id/searchInlineResults"]/android.view.View/android.view.View/android.view.View/android.view.View[3]');
    await podcast.click();

    // Wait for the podcast page to load
    await driver.pause(5000);

    // Click on the star icon to save the episode as a favorite
    const star = await $('~Episode unstarred');
    await star.click();

    // Briefly wait after clicking the star icon
    await driver.pause(3000);

    // Navigate back to the home screen
    await driver.back();

    // Optionally wait to ensure the home screen is fully loaded
    await driver.pause(5000);
});
//-------------------------------------------------------------------------------------
it.only('From the notification panel, clear all the notifications.', async () => {
  // Step 1: Go to the home screen
  await driver.pressKeyCode(3); // 3 is the keycode for Home

  // Step 2: Open the notification panel
  await driver.openNotifications();

  // Step 3: Wait for the "Clear All" button to be visible
  const clearAllBtn = await $('~Clear all notifications.'); // Using accessibility ID
  
  // Wait until the "Clear All" button is displayed (timeout 7 seconds)
  await clearAllBtn.waitForDisplayed({ timeout: 7000 });

  // Step 4: Click on "Clear All"
  await clearAllBtn.click();

  // Step 5: Pause for a few seconds to allow notifications to clear
  await driver.pause(6000); // Wait for 6 seconds for notifications to be cleared

  // Step 6: Go back to the home screen
  await driver.pressKeyCode(3); // Press the Home button again

  // Step 7: Pause for 30 seconds to complete the task
  await driver.pause(30000);
});


//issue3  


it('should switch to another app, copy text, return to Podcast Player, and go to home screen', async () => {
  // Step 1: Switch to another app (Google Assistant or any other app)
  await browser.startActivity('com.google.android.googlequicksearchbox', 'com.google.android.apps.gsa.assistant.handoff.BrowserReturnActivity'); // Replace with correct values

  // Step 2: Perform actions in the other app (copying text)
  // Assuming you have an element to interact with to copy text
  const textElement = await $('~element_id_to_copy_text'); // Replace with the actual element ID or accessibility ID
  await textElement.waitForDisplayed({ timeout: 5000 });
  await textElement.click(); // Click on the text or element to copy it
  
  // Depending on the app, you may need to perform additional steps to copy the text
  await driver.pressKeyCode(29); // Simulate the copy action (use the appropriate key code for copy)

  // Step 3: Return to the Podcast Player app
  await browser.startActivity('au.com.shiftyjelly.pocketcasts', 'au.com.shiftyjelly.pocketcasts.MainActivity'); // Use the correct values here

  // Step 4: Optionally, perform an action with the copied text in the Podcast Player app
  const inputField = await $('~input_field_id'); // Replace with the actual input field ID or accessibility ID
  await inputField.waitForDisplayed({ timeout: 5000 });
  await inputField.click(); // Click to focus the input field
  await driver.setValue('Your copied text here'); // Paste or enter the copied text

  // Step 5: Go to the home screen
  await driver.pressKeyCode(3); // Key code for home
});
});
