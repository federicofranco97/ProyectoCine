
function CloseTicket(Component){
    var TicketId = Component.name;
    window.open("/close_ticket?ticket="+TicketId,"_self");
}