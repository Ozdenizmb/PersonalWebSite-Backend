# personal-website

<h1>This project constitutes the back-end part of my personal website.</h1>


<p>Things you need to pay attention to for the Spring project to run successfully:</p>

<h3>1. Configure yaml file</h3>

<p>Update the database's username and password information to be compatible with your own database.</p>
<p>Make sure you have a database named personal_website</p>

<h3>2. You need to Resource/keystore.p12</h3>

<p>Keep the keystore.p12 file that holds SSL certificates under the resource folder</p>

<h3>3. Create a file named gradle.properties</h3>

<p>adminKey=&lt;YOUR_ADMIN_KEY&gt;</p>
<br>
<p>fileCdnPath=&lt;YOUR_FILE_CDN_PATH&gt;</p>
<br>
<p>datasourceUrl=&lt;YOUR_DATASOURCE_URL (URL+DATABASE_NAME)&gt;</p>
<p>datasourceUsername=&lt;YOUR_DATASOURCE_USERNAME&gt;</p>
<p>datasourcePassword=&lt;YOUR_DATASOURCE_PASSWORD&gt;</p>
<br>
<p>sslKeyPassword=&lt;YOUR_KEYSTORE.P12_PASSWORD (FOR HTTPS REQUEST/SSL CERTIFICATE)&gt;</p>

<br><br><br>
<p><i>For adminSignUpKey, you can randomly write the key you want. However, you must not forget this key information. If you forget this information, you won't be able to register as an admin.</i></p>
<br>
<p><b>For the interface (FrontEnd) to be usable, the Backend must be running. Otherwise, the requests sent from the interface will not be functional.</b></p>
<br>
<p><i>Access the project's front-end from the following link:</i></p>
<p>https://github.com/Ozdenizmb/PersonalWebSite-Frontend</p>
