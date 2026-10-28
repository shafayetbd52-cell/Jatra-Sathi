# যাত্রাসাথী — Android Studio Starter Project

এটি যাত্রাসাথী রাইড-শেয়ারিং অ্যাপের একটি প্রাথমিক নেটিভ Android Studio প্রজেক্ট।

## যা আছে
- CNG, বাইক, রিকশা ও প্রাইভেট কার নির্বাচন
- যাত্রার শুরু ও গন্তব্য লেখার ঘর
- আনুমানিক ভাড়ার ডেমো
- প্রোফাইল স্ক্রিনে নাম ও মোবাইল নম্বর
- বাংলা UI

## এখনো যা বাস্তবায়িত নয়
এটি প্রাথমিক UI ডেমো। বাস্তব লগইন/OTP, সার্ভার, ড্রাইভার-ম্যাচিং, লাইভ GPS, পেমেন্ট এবং কলের জন্য আলাদা ব্যাকএন্ড/সেবা যুক্ত করতে হবে। প্রোফাইলের তথ্য বর্তমানে ডেমো হিসেবে দেখানো হয়; নিরাপদভাবে সংরক্ষণ করা হয় না।

## Android Studio দিয়ে APK তৈরি
1. Android Studio-তে এই ফোল্ডারটি Open করুন।
2. Gradle sync শেষ হতে দিন এবং Android SDK Platform 35 ইনস্টল করুন।
3. **Build > Build Bundle(s) / APK(s) > Build APK(s)** নির্বাচন করুন।
4. APK সাধারণত `app/build/outputs/apk/debug/app-debug.apk`-এ তৈরি হবে।

## GitHub Actions দিয়ে ক্লাউডে APK তৈরি
প্রজেক্টটি GitHub repository-তে আপলোড করলে `.github/workflows/android.yml` স্বয়ংক্রিয়ভাবে APK build করার workflow দেয়। GitHub-এ **Actions** ট্যাব থেকে **Build JatraSathi APK** workflow চালু করুন। সফল হলে run-এর **Artifacts** অংশ থেকে `JatraSathi-debug-apk` ডাউনলোড করা যাবে।

এই চ্যাটের রানটাইমে Android SDK/Gradle build tool নেই, তাই এখানে APK সরাসরি compile করা যায়নি।
