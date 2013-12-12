$(document).ready(function(){
    loadAgents();
    $('.agent').click(function(){
        agentClick(this);
    });
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
             + "<div class='feedbacks'>" + agent.feedbacks + "</div>"
             + "<div class='behaviour'>" + agent.behaviour + "</div>"
             + "<div class='categories'>" + agent.categories + "</div>"
             + "</div>";
    $('.agents').append(html);
}

function agentClick(elem){
    $('#agentModal').modal('show');
    var name = $(elem).attr("id");
    $('#agentModalTitle').html(name);
    $('tbody').html("");
    var feedbacks = getAgentFeedbacks(name);
    for (index = 0; index < feedbacks.length; ++index) {
        addFeedback(feedbacks[index]);
    }
}

function getAgentFeedbacks(agent){
    $.ajaxSetup( { "async": false } );
    var data = $.getJSON("getAgentFeedbacks/" +  agent);
    $.ajaxSetup( { "async": true } );
    return $.parseJSON(data["responseText"]);
}

function addFeedback(feedback){
    var html = "<tr><td>" + feedback.ticks + "</td>"
             + "<td>" + feedback.score + "</td>"
             + "<td>" + feedback.category + "</td>"
             + "<td>" + feedback.product + "</td>"
             + "<td>" + feedback.buyer + "</td>"
             + "</tr>";
    $('tbody').append(html);
}
