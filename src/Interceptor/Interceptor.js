import axios from 'axios'  //引入axios
import CryptoJS from 'crypto-js';
import { Notify } from 'vant';

let cyKey = "Dn0lfj3sGGh73XOZ";

function encrypt(word) {
    var key = CryptoJS.enc.Utf8.parse(cyKey);
    var srcs = CryptoJS.enc.Utf8.parse(word);
    var encrypted = CryptoJS.AES.encrypt(srcs, key, { mode: CryptoJS.mode.ECB, padding: CryptoJS.pad.Pkcs7 });
    return encrypted.toString();
}

function decrypt(word) {
    var key = CryptoJS.enc.Utf8.parse(cyKey);
    var decrypt = CryptoJS.AES.decrypt(word, key, { mode: CryptoJS.mode.ECB, padding: CryptoJS.pad.Pkcs7 });
    return CryptoJS.enc.Utf8.stringify(decrypt).toString();
}

let instance = axios.create({
    timeout: 300000,
    responseType: "json",
    withCredentials: true,
})

instance.interceptors.request.use(
    config => {
        var token = localStorage.getItem("retoken");
        if (token != null) {
            config.headers.retoken = token;
        }
        config.data = { data: encrypt(JSON.stringify(config.data)) };
        return config;
    },
    error => {
        Notify({ type: 'danger', message: '请求错误' });
        return Promise.reject(error);
    }
)

instance.interceptors.response.use(
    response => {
        var message = "成功";
        if (response.data != null) {
            message = response.data.message;
        }
        Notify({ type: 'success', message: message })
        return response;
    },
    error => {
        var message = error.response.statusText;
        if (error.response.data != null) {
            var mesg = error.response.data.message;
            switch (error.response.status) {
                case 403: message = "登陆过期，请重新登陆!"; localStorage.removeItem("retoken"); break;
                case 404: message = "请求的页面不存在!"; break;
                case 500: message = "服务器响应错误!"; break;
                default: message = mesg;
            }
            if (mesg == null) {
                message = "发生了一个未知错误";
            }
        }
        Notify({ type: 'danger', message: message });
        return Promise.reject(error);
    }
)

let instance2 = axios.create({
    timeout: 10000,
    responseType: "json",
    withCredentials: true,
})

instance2.interceptors.request.use(
    config => {
        var token = localStorage.getItem("retoken");
        if (token != null) {
            config.headers.retoken = token;
        }
        config.data = { data: encrypt(JSON.stringify(config.data)) };
        return config;
    },
    error => {
        Notify({ type: 'danger', message: '请求错误' });
        return Promise.reject(error);
    }
)

instance2.interceptors.response.use(
    response => {
        return response;
    },
    error => {
        var message = error.response.statusText;
        if (error.response.data != null) {
            var mesg = error.response.data.message;
            if (mesg == null) {
                message = "发生了一个未知错误";
            }
        } else {
            switch (error.response.status) {
                case 403: message = "禁止访问!";
                    localStorage.removeItem("retoken");
                    sessionStorage.removeItem("userid");
                    ; break;
                case 404: message = "请求的页面不存在!"; break;
                case 500: message = "服务器响应错误!"; break;
            }
        }
        if (message == null || message == "") {
            message = "发生了一个未知错误";
        }
        Notify({ type: 'danger', message: message });
        return Promise.reject(error);
    }
)

export function fetchGet(url, param) {
    return new Promise((resolve, reject) => {
        instance2.get(url, { params: param })
            .then(response => {
                resolve(response.data)
            }, err => {
                reject(err)
            })
            .catch((error) => {
                Notify({ type: 'danger', message: "获取数据失败" });
                reject(error)
            })
    })
}

export function fetchPost(url, params) {
    return new Promise((resolve, reject) => {
        instance2.post(url, params)
            .then((response) => {
                resolve(response.data)
            }, err => {
                reject(err)
            })
            .catch((error) => {
                reject(error)
            })
    })
}

export default instance