apiclient = (function (){
    //var zelda = "http://localhost:36000"
    var zelda = "https://salty-journey-22242.herokuapp.com"



    function getRestaurants(){
        $("#restaurants").html("");
        axios.get(zelda+"/restaurants").then(res=>
            res.data.map((restaurant) => {
                console.log(res.data)
            $("#restaurants").append(
                "<tr> " +
                "<th>" +
                restaurant.name +
                "</th>" +
                "<th>" +
                restaurant.borough +
                "</th>" +
                "<th>" +
                restaurant.cuisine +
                "</th>" +
                "<th>" +
                restaurant.restaurant_id +
                "</th>"+
                "</tr>");
            }))
    }

    function addRestaurant(){
        var nameD = document.getElementById("name").value;
        var boroughD = document.getElementById("borough").value;
        var cuisineD = document.getElementById("cuisine").value;
        var restaurantid = document.getElementById("restaurantid").value;

        axios({
            method :'post',
            url: zelda + '/restaurants',
            data: {
                name : nameD,
                borough : boroughD,
                cuisine : cuisineD,
                restaurant_id : restaurantid
            }
        }).then((response)=>{
            getRestaurants();
        })

    }
    return{
        getRestaurants : getRestaurants,
        addRestaurant : addRestaurant
    }
})();
