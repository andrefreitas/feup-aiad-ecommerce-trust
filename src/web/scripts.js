$(document).ready(function(){
    loadAgents();
});

function loadAgents(){
    var agents = getAgents();
    for (index = 0; index < agents.length; ++index) {
        addAgent(agents[index]);
    }
}

function getAgents() {
    $.ajaxSetup( { "async": false } );
     var data = $.getJSON("getAgents");
    $.ajaxSetup( { "async": true } );
    return $.parseJSON(data["responseText"]);
}

function addAgent(agent){
    var html = "<div class='agent' id='" + agent.name + "'>"
             + "<div class='name'>" + agent.name + "</div>"
             + "<div class='behaviour'>" + agent.behaviour + "</div>"
             + "<div class='categories'>" + agent.categories + "</div>"
             + "</div>";
    $('.agents').append(html);
}