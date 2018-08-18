1) add permissions to app
	internet
	storage read and write
	
	not added:
		firebase push key
		for generating random number
		for our image store in storage firebase

2) circle image library used for showing profile pic
in SettingActivity
(https://github.com/hdodenhof/CircleImageView)

3) crop library
(https://github.com/ArthurHub/Android-Image-Cropper)

4) android random string generator
public static String random() {
    Random generator = new Random();
    StringBuilder randomStringBuilder = new StringBuilder();
    int randomLength = generator.nextInt(MAX_LENGTH);
    char tempChar;
    for (int i = 0; i < randomLength; i++){
        tempChar = (char) (generator.nextInt(96) + 32);
        randomStringBuilder.append(tempChar);
    }
    return randomStringBuilder.toString();
}

5) picasso library (http://square.github.io/picasso/)
	it maps the url to add value listener helps to
	to put image in app using url
6) firebase ui 1.2.0
	(https://github.com/firebase/FirebaseUI-Android)

7) compressor (https://github.com/zetbaitsu/Compressor)
	
	for notification feature working with firebase functions
8) install node js >5 version and activate npm
	install -->	npm install -g firebase-tools

9) create folder notificationFunction cd to dir and
	--> firebase login
	--> firebase init	select function
	made changes in index.js
	replace %RESOURCE_DIR% in firebase.json
	--> firebase deploy

	npm install firebase-functions@latest firebase-admin@latest --save
	npm install -g firebase-tools
	event replace with context

10) for icons
	--> https://design.google/resources/


v number complete: 32