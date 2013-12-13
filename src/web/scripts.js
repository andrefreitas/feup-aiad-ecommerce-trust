$(document).ready(function() {
    loadAgents();
    $('.agent').click(function() {
        agentClick(this);
    });

    $('#computeTrustBt').click(function() {
        computeTrustClick();
    });

    $('#addFeedbackBt').click(function() {
        showfeedbackClick();
    });
    $('#postFeedbackBt').click(function() {
        addNewFeedbackClick();
    });
});

function loadAgents() {
    var agents = getAgents();
    for (index = 0; index < agents.length; ++index) {
        addAgent(agents[index]);
    }
}

function getAgents() {
    $.ajaxSetup({"async": false});
    var data = $.getJSON("getAgents");
    $.ajaxSetup({"async": true});
    return $.parseJSON(data["responseText"]);
}

function addAgent(agent) {
    var html = "<div class='agent' id='" + agent.name + "'>"
            + "<div class='name'>" + agent.name + "</div>"
            + "<div class='feedbacks'>" + agent.feedbacks + "</div>"
            + "<div class='behaviour'>" + agent.behaviour + "</div>"
            + "<div class='categories'>" + agent.categories + "</div>"
            + "</div>";
    $('.agents').append(html);
}

function agentClick(elem) {
    $('#trustComputed').html("");
    $('#agentModal').modal('show');
    var name = $(elem).attr("id");
    $('#agentModalTitle').html(name);
    $('tbody').html("");
    $('#agentTrust').html("0");
    var trust = getAgentTrust(name, "<none>", "<none>");
    trust = trust.toString().substr(0, 4);
    $('#agentTrust').html(trust);
    var feedbacks = getAgentFeedbacks(name);
    for (index = 0; index < feedbacks.length; ++index) {
        addFeedback(feedbacks[index]);
    }

}

function getAgentFeedbacks(agent) {
    $.ajaxSetup({"async": false});
    var data = $.getJSON("getAgentFeedbacks/" + agent);
    $.ajaxSetup({"async": true});
    return $.parseJSON(data["responseText"]);
}

function addFeedback(feedback) {
    var html = "<tr><td>" + feedback.ticks + "</td>"
            + "<td>" + feedback.score + "</td>"
            + "<td>" + feedback.category + "</td>"
            + "<td>" + feedback.product + "</td>"
            + "<td>" + feedback.buyer + "</td>"
            + "</tr>";
    $('tbody').append(html);
}

function getAgentTrust(agentName, category, product) {
    $.ajaxSetup({"async": false});
    var data = $.getJSON("getAgentTrust/" + agentName + "/" + category + "/" + product);
    $.ajaxSetup({"async": true});
    return $.parseJSON(data["responseText"])["trust"];
}

function computeTrustClick() {
    $('#trustComputed').html("");
    var agentName = $('#agentModalTitle').html();
    var category = $('#categoryInput').val();
    var product = $('#productInput').val();
    if (category !== "" && product !== "") {
        var trust = getAgentTrust(agentName, category, product);
        trust = trust.toString().substr(0, 4);
        $('#trustComputed').html("Result: " + trust + "/5");
    }
}

function showfeedbackClick() {
    $('#newFeedbackAdded').html("");
    if ($(".addFeedback").is(':visible')) {
        $('.addFeedback').fadeOut();
    } else {
        $('.addFeedback').fadeIn();
    }
}

function addNewFeedbackClick() {
    $('#newFeedbackAdded').html("");
    var targetagentName = $('#agentModalTitle').html();
    var category = $('#categoryFbInput').val();
    var product = $('#productFbInput').val();
    var score = $('#scoreFbInput').val();
    var tick = $('#tickFbInput').val();
    var owner = $('#ownerFbInput').val();
    if (category !== "" && product !== "" && score !== "" 
            && tick !== "" && owner !== "") {
        var requestResult = addNewFeedBack(targetagentName,category,product,score,tick,owner);
        $('#newFeedbackAdded').html(requestResult);      
    }
}

function addNewFeedBack(targetagentName,category,product,score,tick,owner) {
    $.ajaxSetup({"async": false});
    var data = $.getJSON("newFeedback/" + targetagentName + "/" + category + "/" 
            + product + "/" + score + "/" + tick + "/" + owner);
    $.ajaxSetup({"async": true});
    return $.parseJSON(data["responseText"]);
}