<template>
 
  <div class="gradient-border" id="box">
    
    <ul>
      <p class="ip">当前IP地址：<span>{{ ip }}</span></p>
       <li id="diqu"><button @click="openDialog()">{{ userCity }}</button></li> 
      <li><a :href="fxLink" target="_blank"><i :class="['qi-'+ weather.icon+'-fill']"></i></a></li>
      <li id="temp">{{weather.temp}}℃</li>
      <li id="wind">{{weather.windDir}} - {{weather.windScale}}级</li>
    </ul>
  </div>



 <el-dialog :title="title" v-model="this.visiale" width="400px">
    请输入城市：<input  v-model="userCity">
    <button class="queding" @click="onClick(userCity)">确定</button>
  </el-dialog>
</template>

<script>
import api from '@/api/api';


export default {
  name : 'weather',
  data(){
    return{
      fxLink:'',
      userCity:'',
      ip:'',
      weather:{
        temp:'',
        icon:'',
        windDir:'',
        text:'',
        windScale:''
      },
      title:'',
      visiale: false,
    }
  },

  methods:{
    getWeather(){

     api.getWeather(this.userCity).then(({ data })=>{
       if (data.flag){
        console.log(data.data)
         this.weather = data.data.now;
         this.fxLink = data.data.fxLink;
       }
     })

    },  
    getWeatherByIp(){
      api.getUserCity().then(({ data })=>{
        if(data.code){
          this.ip = data.data.ip;
          if(data.data.ad_info.district==''){
            this.userCity = '大连';
          }else {
            this.userCity = data.data.ad_info.district;
           }
        
          this.getWeather();
        }

      })
    },
    openDialog(){
      this.title = '请输入城市'
     var button =  document.getElementById('diqu');
     button.addEventListener("click", function() {
    button.style.border = "none";
    button.style.outline = "none";
      });
      this.visiale = true;
    },

    onClick(userCity){
      if (userCity=='') {
        this.userCity = '大连';
       }else {
        this.userCity = userCity;
        }
      
      
      this.getWeather();
      this.visiale = false;
    }

  },
  mounted() {
    
    this.getWeatherByIp();
    
   
  }
}



</script>



<style>
@import url('https://fonts.googleapis.com/css?family=Raleway:200');

el-dialog{
  width: 400px;
  height: 220px;
}
.queding{
  width: 50px;
  height: 30px;
  padding: 10px;
  align-content: center;
  background-color: #07b39b;
  font-size: 15px;
  float: right;
  line-height: 15px;
  
}
input{
  border:#ef4e7b,solid,1px;
  width: 200px;
}
#dipu button{
  border: none;
  outline: none;
}

#dipu{
  width: 120px;
}
#temp{
  width: 50px;
}

#box li{
  text-decoration: none;
  list-style: none;
  float: left;
  padding-left: 10px;
  padding-right: 5px;
  align-content: left;
}

.ip{
  margin-bottom: 10px;
  margin-left: 10px;
  align-items: center;
  align-content: center;
}

.ip span{
  color: black;
  font-weight: bold;
  font-size: 15px;
}



#box {
    align-content: center;
    display: flex;
    align-items: center;
    justify-content: center;
    width: 270px;
    height: 70px;
    color: rgb(223, 227, 214);
     margin-left:  13%; 
   /*  font-family: 'Raleway'; */
    font-size: 14px;
}
.gradient-border {
    --borderWidth: 3px;
    position: relative;
    border-radius: var(--borderWidth);
}
.gradient-border:after {
    content: '';
    position: absolute;
    top: calc(-1 * var(--borderWidth));
    left: calc(-1 * var(--borderWidth));
    height: calc(100% + var(--borderWidth) * 2);
    width: calc(100% + var(--borderWidth) * 2);
    background: linear-gradient(60deg, #f79533, #f37055, #ef4e7b, #a166ab, #5073b8, #1098ad, #07b39b, #6fba82);
    border-radius: calc(2 * var(--borderWidth));
    z-index: -1;
    animation: animatedgradient 3s ease alternate infinite;
    background-size: 300% 300%;
}


@keyframes animatedgradient {
    0% {
        background-position: 0% 50%;
    }
    50% {
        background-position: 100% 50%;
    }
    100% {
        background-position: 0% 50%;
    }
}



</style>
