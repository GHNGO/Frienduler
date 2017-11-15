
<html>
<head>
 
  	<title>Fixed table header</title>
 	
</head>

<body>
<section class="wrapper">
<section>
  <!--for demo wrap-->
  <h1>My Friends</h1>
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
        <tr>
        
          <th>Add</th>
          <th>Friends</th>
    
         
        </tr>
      </thead>
    </table>
  </div>
  <div class="tbl-content">
    <table cellpadding="0" cellspacing="0" border="0">
      <tbody>
        <tr>
          	<td>
          		<form>
          			<input type="checkbox" value="1">
          		</form>
          	</td>
          
          <!--Dummy name-->
          <td>loyed</td> 
 
        </tr>
  
      </tbody>
    </table>
  </div>
</section>
  <section>
  <!--for demo wrap-->
  <h1>Events</h1>
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0" >
      <thead>
        <tr>
        
          <th>Event</th>
          <th>Begin Date</th>
          <th>End Date</th>
         
        </tr>
      </thead>
    </table>
  </div>
  <div class="tbl-content">
    <table cellpadding="0" cellspacing="0" border="0">
      <tbody>
        <tr>
          <td>Party</td>
          <td>11/20/2018 </td>
          <td>11/22/2018</td>
        </tr>
    <tr>
          <td>Party</td>
          <td>11/20/2018 </td>
          <td>11/22/2018</td>

        </tr>
   
      </tbody>
    </table>
  </div>
</section>

</section>
<style>

section {
   margin: 50px;
}

.wrapper{display:grid; grid-template-columns:25% 75%;}
.wrapper > section {width:90%; float: left; padding:1em;}
 table{
    width:100%;
    table-layout: fixed;
  }
  .inlineTable{
  	dispaly: inline-block;
  	}
  	
  h1{
    font-size: 30px;
    color: #fff;
    text-transform: uppercase;
    font-weight: 300;
    text-align: center;
    margin-bottom: 15px;
  }
  .tbl-header{
    background-color: rgba(255,255,255,0.3);
  }
  .tbl-content{
    height:300px;
    overflow-x:auto;
    margin-top: 0px;
    border: 1px solid rgba(255,255,255,0.3);
    background-color: rgba(0,0,0,.6);
  }
  th{
    padding: 20px 15px;
    text-align: left;
    font-weight: 500;
    font-size: 12px;
    color: rgba(20,57,78,1);
    text-transform: uppercase;
  }
  td{
    padding: 15px;
    text-align: left;
    vertical-align:middle;
    font-weight: 300;
    font-size: 12px;
    color: #fff;
    border-bottom: solid 1px rgba(255,255,255,0.1);
  }


  /* demo styles */

  @import url(https://fonts.googleapis.com/css?family=Roboto:400,500,300,700);
  body{
    background: -webkit-linear-gradient(left, #25c481, #25b7c4);
    background: linear-gradient(to right, #25c481, #25b7c4);
    font-family: 'Roboto', sans-serif;
  }
  section{
    margin: 50px;
  }
  	
</style>



</body>
</html>