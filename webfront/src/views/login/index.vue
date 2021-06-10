<template>
  <div class="login-container">
    <img class="login-img" src="@/assets/login_back.png">
    <el-card class="box-card">
      <el-form
        ref="loginForm"
        :model="loginForm"
        :rules="loginRules"
        class="login-form"
        auto-complete="on"
        label-position="left"
      >
        <el-form-item prop="userName">
          <span class="svg-container">
            <svg-icon icon-class="user" />
          </span>
          <el-input
            id="account"
            ref="userName"
            v-model="loginForm.userName"
            placeholder="用户名"
            name="userName"
            type="text"
            tabindex="1"
            auto-complete="on"
          />
        </el-form-item>

        <el-form-item prop="password">
          <span class="svg-container">
            <svg-icon icon-class="password" />
          </span>
          <el-input
            id="psw"
            :key="passwordType"
            ref="password"
            v-model="loginForm.password"
            :type="passwordType"
            placeholder="密码"
            name="password"
            tabindex="2"
            auto-complete="on"
            @keyup.enter.native="handleLogin"
          />
          <span class="show-pwd" @click="showPwd">
            <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" />
          </span>
        </el-form-item>

        <el-button
          id="login_btn"
          :loading="loading"
          type="primary"
          style="width:100%;margin-bottom:30px;"
          @click.native.prevent="handleLogin"
        >登录</el-button>
      </el-form>
    </el-card>
  </div>
</template>

<script>
    import md5 from "js-md5";
    export default {
        name: "Login",
        data() {
            const validateuserName = (rule, value, callback) => {
                if (value.length < 3) {
                    callback(new Error("Please enter the correct user name"));
                } else {
                    callback();
                }
            };
            const validatePassword = (rule, value, callback) => {
                if (value.length < 4) {
                    callback(new Error("The password can not be less than 6 digits"));
                } else {
                    callback();
                }
            };
            return {
                loginForm: {
                    userName: "",
                    password: ""
                },
                loginRules: {
                    userName: [
                        { required: true, trigger: "blur", validator: validateuserName }
                    ],
                    password: [
                        { required: true, trigger: "blur", validator: validatePassword }
                    ]
                },
                loading: false,
                passwordType: "password",
                redirect: undefined
            };
        },
        watch: {
            $route: {
                handler: function(route) {
                    this.redirect = route.query && route.query.redirect;
                },
                immediate: true
            }
        },
        methods: {
            showPwd() {
                if (this.passwordType === "password") {
                    this.passwordType = "";
                } else {
                    this.passwordType = "password";
                }
                this.$nextTick(() => {
                    this.$refs.password.focus();
                });
            },
            handleLogin() {
                let that = this;
                this.loading = true;
                //数据格式验证
                // this.$refs.loginForm.validate(valid => {
                //   if (valid) {
                //     localStorage.setItem("hasLogin", true);
                //     this.$router.push({ path: "/" });
                //   } else {
                //     console.log("验证失败");
                //   }
                // });
                // return 0;
                // 可自定义登录时的逻辑处理
                console.log("111111");
                this.req({
                    url: "login",
                    data: this.loginForm,
                    method: "POST"
                }).then(
                    res => {
                        console.log("res :", res);
                        localStorage.setItem("hasLogin", true);
                        this.loading = false;
                         const message = res.data.statusMsg;
                        if(res.data.statusCode === '20000'){
                            this.$router.push({ path: "/" });
                            this.$message.success(message);
                        }else{
                             this.$message.error(message);
                        }
                    },
                    err => {
                        console.log("err :", err);
                        this.passwordError = true;
                        this.loading = false;
                    }
                );
            }
        }
    };
</script>

<style lang="scss">
  /* 修复input 背景不协调 和光标变色 */
  /* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */
  .info {
    position: fixed;
    bottom: 20px;
    width: 100%;
    text-align: center;
    color: gainsboro;
  }
  $bg: #283443;
  $light_gray: #fff;
  $cursor: #fff;

  @supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
    .login-container .el-input input {
      color: $cursor;
    }
  }

  /* reset element-ui css */
  .login-container {
    .el-input {
      display: inline-block;
      height: 45px;
      width: 85%;
      input {
        background: transparent;
        border: 0px;
        -webkit-appearance: none;
        padding: 8px 5px 12px 15px;
        color: $light_gray;
        caret-color: $cursor;
        &:-webkit-autofill {
          box-shadow: 0 0 0px 1000px #4070ac inset !important;
          -webkit-text-fill-color: $cursor !important;
        }
      }
    }

    .el-form-item {
      border: 1px solid rgba(255, 255, 255, 0.1);
      background: rgba(0, 0, 0, 0.1);
      border-radius: 5px;
      color: rgba(0, 0, 0, 0.1);

    }
    .box-card {
      position: absolute;
      left: 0;
      right: 0;
      top: 200px;
      width: 580px;
      height: 330px;
      background-color: rgba(0,0,0,0.1);
      margin: 0px auto;
    }

  }
</style>

<style lang="scss" scoped>
  $bg: #2d3a4b;
  $dark_gray: #889aa4;
  $light_gray: #eee;

  .login-container {
    position: absolute;
    width: 100%;
    height: 100%;
    overflow: hidden;

    .login-form {
      position: relative;
      width: 400px;
      max-width: 100%;
      padding: 50px 35px 0;
      margin: 0 auto;
      overflow: hidden;
    }

    .tips {
      font-size: 14px;
      color: #fff;
      margin-bottom: 10px;

      span {
        &:first-of-type {
          margin-right: 16px;
        }
      }
    }

    .svg-container {
      padding: 6px 5px 6px 15px;
      color: $dark_gray;
      vertical-align: middle;
      width: 30px;
      display: inline-block;
    }

    .title-container {
      position: relative;

      .title {
        font-size: 26px;
        color: $light_gray;
        margin: 0px auto 40px auto;
        text-align: center;
        font-weight: bold;
      }
    }

    .show-pwd {
      position: absolute;
      right: 10px;
      top: 7px;
      font-size: 16px;
      color: $dark_gray;
      cursor: pointer;
      user-select: none;
    }
    .login-img {
      width: 100%;
      height: 100%;
    }
  }
</style>
