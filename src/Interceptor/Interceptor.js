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
    timeout: 10000,
    responseType: "json",
    withCredentials: true,
})

instance.interceptors.request.use(
    config => {
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
            message = response.data;
        }
        Notify({ type: 'success', message: message })
        return response;
    },
    error => {
        var message = error.response.statusText;
        switch (error.response.status) {
            case 403: message = "登陆过期，请重新登陆!"; break;
            case 404: message = "请求的页面不存在!"; break;
            case 500: message = "服务器响应错误!"; break;
            default: message = error.response.data.message;
        }
        Notify({ type: 'danger', message: message });
    }
)

export default instance