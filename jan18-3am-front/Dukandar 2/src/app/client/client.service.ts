import { environment } from './../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ClientService {
  // url = "http://localhost:8080/dukandar"

  constructor(private http: HttpClient) {}

  userSignup(body) {
    return this.http.post(environment.baseURL + '/auth/user-signup', body);
  }

  loadShopsWithinRadius(latitude, longitude, radius) {
    const headers = new HttpHeaders()
      .set('content-type', 'application/json')
      .set('token', `${localStorage.getItem('token')}`);
    console.log(
      environment.baseURL +
        `/user/shops-in-radius?latitude=${latitude}&longitude=${longitude}&radius=${radius}`
    );

    return this.http.get(
      environment.baseURL +
        `/user/shops-in-radius?latitude=${latitude}&longitude=${longitude}&radius=${radius}`,
      { headers: headers }
    );
  }

  getProfile() {
    // add the token in the request header
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        token: `${localStorage.getItem('token')}`,
      }),
    };
    console.log('Token in service ' + `${localStorage.getItem('token')}`);

    return this.http.get(environment.baseURL + '/user/profile', httpOptions);
  }

  uploadUserImage(selectedFile) {
    const uploadData = new FormData();
    uploadData.append('file', selectedFile, selectedFile.name);
    uploadData.append('token', `${localStorage.getItem('token')}`);
    return this.http.post(
      environment.baseURL + '/user/upload-file',
      uploadData
    );
  }

  saveUserinfo(
    firstName: String,
    lastName: String,
    mobileNo: Number,
    email: String,
    gender: String,
    age: Number,
    streetadd1: String,
    streetadd2: String,
    city: String,
    state: String,
    pincode: Number,
    longitude: Number,
    latitude: Number
  ) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        token: `${localStorage.getItem('token')}`,
      }),
    };
    const body = {
      firstName: firstName,
      lastName: lastName,
      mobile: mobileNo,
      email: email,
      gender: gender,
      age: age,
      streetadd1: streetadd1,
      streetadd2: streetadd2,
      city: city,
      state: state,
      pincode: pincode,
      longitude: longitude,
      latitude: latitude,
    };
    console.log('body frontend ' + body['pincode']);

    console.log('body frontend ' + body['state']);
    return this.http.post(
      environment.baseURL + '/user/save-user',
      body,
      httpOptions
    );
  }

  getUserDetail() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        token: `${localStorage.getItem('token')}`,
      }),
    };
    return this.http.get(environment.baseURL + `/user/profile`, httpOptions);
  }

  getHomeshopInfos() {
    return this.http.get(environment.baseURL + '/client/shop-infos');
  }

  likeincrerement(shopid: any) {
    return this.http.get(
      environment.baseURL + `/shop/like-increment?shopId=${shopid}`
    );
  }

  addToFev(shopid: any) {
    const headers = new HttpHeaders().set(
      'token',
      `${localStorage.getItem('token')}`
    );

    return this.http.get(
      environment.baseURL + `/shop/add-tofev?shopId=${shopid}`,
      {
        headers: headers,
      }
    );
  }

  private _radius: number = 0;
  get radius(): number {
    return this._radius;
  }
  set radius(value: number) {
    this._radius = value;
  }

  private _shopId: number = 0;
  get shopId(): number {
    return this._shopId;
  }
  set shopId(value: number) {
    this._shopId = value;
  }

  private _shopInfo: object = null;

  get shopInfo(): object {
    return this._shopInfo;
  }
  set shopInfo(value: object) {
    this._shopInfo = value;
  }

  getShopInfo(shopid, latitude, longitude) {
    this._shopId = shopid;
    return this.http.get(
      environment.baseURL +
        `/user/get-shop-details?shopId=${shopid}&latitude=${latitude}&longitude=${longitude}`
    );
  }

  getAllPosts(shopid) {
    return this.http.get(
      environment.baseURL + `/user/get-all-posts?shopId=${shopid}`
    );
  }

  getAllReviews(shopid) {
    return this.http.get(
      environment.baseURL + `/user/get-all-reviews?shopId=${shopid}`
    );
  }

  saveReview(comment, rating, shopId) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        token: `${localStorage.getItem('token')}`,
      }),
    };

    const body = {
      comment: comment,
      rating: rating,
    };

    return this.http.post(
      environment.baseURL + `/shop/add-review-toshop?shopId=${shopId}`,
      body,
      httpOptions
    );
  }

  saveQuery(query, shopId) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        token: `${localStorage.getItem('token')}`,
      }),
    };

    const body = {
      query: query,
    };

    return this.http.post(
      environment.baseURL + `/shop/add-enquiry-toshop?shopId=${shopId}`,
      body,
      httpOptions
    );
  }

  getAllShopsPosts() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        token: `${localStorage.getItem('token')}`,
      }),
    };

    return this.http.get(
      environment.baseURL + `/user/getAllShopsPosts`,
      httpOptions
    );
  }
}
