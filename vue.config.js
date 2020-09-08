module.exports = {
  publicPath: '/',
  devServer: {
    open: true,
    // host: '192.168.1.10',
    host: 'www.demo.com',
    https: false,
    port: 8081,
    proxy: {
      '/prmusic': {
        // target: 'https://servers.prmusic.xyz/prmusic/',
        target: 'http://www.demo.com:8080/prmusic/',
        ws: true,
        changOrigin: true,
        pathRewrite: {
          '^/prmusic': '/'
        }
      }
    }
  },
  pluginOptions: {
    svgLoader: {
      svgo: {
        plugins: []
      }
    }
  },
}
