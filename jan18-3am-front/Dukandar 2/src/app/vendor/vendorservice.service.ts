import { environment } from './../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Time } from '@angular/common';

@Injectable({
  providedIn: 'root',
})
export class VendorserviceService {
  //url = 'http://localhost:8080/dukandar';

  firstName = '';
  lastName = '';
  mobileNo = 0;
  email = '';
  gender = '';
  age = 0;

  shopName = '';
  businesscategory = '';
  category = '';
  speciality = '';
  openingtime = '';
  closingtime = '';
  resonetoVisit = '';
  longitude = 0;
  latitude = 0;

  streetadd1 = '';
  streetadd2 = '';
  city = '';
  state = '';
  pincode = '';

  constructor(private http: HttpClient) {}

  uploadVendorImage(selectedFile) {
    const uploadData = new FormData();
    uploadData.append('file', selectedFile, selectedFile.name);
    uploadData.append('token', `${localStorage.getItem('token')}`);
    return this.http.post(
      environment.baseURL + '/vendor/upload-file',
      uploadData
    );
  }

  savepersonalinfo(
    firstName: String,
    lastName: String,
    mobileNo: Number,
    email: String,
    gender: String,
    age: Number
  ) {
    const body = {
      firstName: firstName,
      lastName: lastName,
      mobile: mobileNo,
      email: email,
      gender: gender,
      age: age,
    };

    const headers = new HttpHeaders().set(
      'token',
      `${localStorage.getItem('token')}`
    );

    return this.http.post(environment.baseURL + '/vendor/personal-info', body, {
      headers: headers,
    });

    // return this.HttpClient.post()
  }

  loadDomainInfo() {
    const headers = new HttpHeaders().set(
      'token',
      `${localStorage.getItem('token')}`
    );

    return this.http.get(environment.baseURL + '/vendor/get-domain-info', {
      headers: headers,
    });
  }

  //,businesscategory:String,category:String
  saveshopdetails(
    shopName: String,
    speciality: String,
    openingtime: String,
    closingtime: String,
    reasontoVisit: String,
    businesscategory: String
  ) {
    const body = {
      shopName: shopName,
      speciality: speciality,
      opening_time: openingtime,
      closing_time: closingtime,
      reasontoVisit: reasontoVisit,
    };

    const headers = new HttpHeaders().set(
      'token',
      `${localStorage.getItem('token')}`
    );

    return this.http.post(
      environment.baseURL +
        `/shop/save-shop-details?businesscategory=${businesscategory}`,
      body,
      { headers: headers }
    );
  }

  saveaddressinfo(
    streetadd1: String,
    streetadd2: String,
    city: String,
    state: String,
    pincode: String,
    longitude: Number,
    latitude: Number
  ) {
    const body = {
      streetadd1: streetadd1,
      streetadd2: streetadd2,
      city: city,
      state: state,
      pincode: pincode,
      longitude: longitude,
      latitude: latitude,
    };

    const headers = new HttpHeaders().set(
      'token',
      `${localStorage.getItem('token')}`
    );

    return this.http.post(
      environment.baseURL + '/shop/save-shop-address',
      body,
      {
        headers: headers,
      }
    );
  }

  getShopAddress() {
    const headers = new HttpHeaders().set(
      'token',
      `${localStorage.getItem('token')}`
    );

    return this.http.get(environment.baseURL + '/vendor/get-shop-address', {
      headers: headers,
    });
  }

  vendorSignup(body, shopName, domain) {
    return this.http.post(
      environment.baseURL +
        `/auth/vendor-signup?shopName=${shopName}&domain=${domain}`,
      body
    );
  }

  getAllDomains() {
    return this.http.get(environment.baseURL + `/get-all-domains`);
  }

  loadPersonalInfo() {
    const headers = new HttpHeaders().set(
      'token',
      `${localStorage.getItem('token')}`
    );

    return this.http.get(environment.baseURL + '/vendor/personal-info', {
      headers: headers,
    });
  }

  loadShopInfo() {
    const headers = new HttpHeaders().set(
      'token',
      `${localStorage.getItem('token')}`
    );

    return this.http.get(environment.baseURL + '/vendor/shop-info', {
      headers: headers,
    });
  }

  uploadShopPhoto(selectedFile) {
    const uploadData = new FormData();
    uploadData.append('file', selectedFile, selectedFile.name);
    uploadData.append('token', `${localStorage.getItem('token')}`);
    return this.http.post(
      environment.baseURL + '/vendor/upload-shop-photo',
      uploadData
    );
  }

  getAllShopPhotos() {
    const headers = new HttpHeaders().set(
      'token',
      `${localStorage.getItem('token')}`
    );

    return this.http.get(environment.baseURL + '/vendor/get-shop-photos', {
      headers: headers,
    });
  }

  saveVendorPost(offername, description, offertill, insertedPostId) {
    const body = {
      offername: offername,
      description: description,
      valid_till: offertill,
    };

    const headers = new HttpHeaders()
      .set('content-type', 'application/json')
      .set('token', `${localStorage.getItem('token')}`)
      .set('postId', `${insertedPostId}`);

    return this.http.post(environment.baseURL + '/vendor/save-post', body, {
      headers: headers,
    });
  }

  getAllPostsOfVendor() {
    const headers = new HttpHeaders()
      .set('content-type', 'application/json')
      .set('token', `${localStorage.getItem('token')}`);

    return this.http.get(environment.baseURL + '/vendor/get-all-posts', {
      headers: headers,
    });
  }

  uploadVendorPostImage(selectedFile: File) {
    const uploadData = new FormData();
    uploadData.append('file', selectedFile, selectedFile.name);
    uploadData.append('token', `${localStorage.getItem('token')}`);
    return this.http.post(
      environment.baseURL + '/vendor/upload-post-image',
      uploadData
    );
  }

  getAllShopReviews() {
    const headers = new HttpHeaders().set(
      'token',
      `${localStorage.getItem('token')}`
    );

    return this.http.get(environment.baseURL + '/vendor/shop-reviews', {
      headers: headers,
    });
  }

  getshopInfo() {
    const headers = new HttpHeaders().set(
      'token',
      `${localStorage.getItem('token')}`
    );

    return this.http.get(environment.baseURL + '/vendor/shop-info', {
      headers: headers,
    });
  }

  getAllEnquiries() {
    const headers = new HttpHeaders().set(
      'token',
      `${localStorage.getItem('token')}`
    );

    return this.http.get(environment.baseURL + '/vendor/shop-enquiries', {
      headers: headers,
    });
  }
}
