# OptimizedShare

A share application created to calculate shares in CSCTF/CTF for a game called Conquer online, but can be used for any general purposes to equally share rewards from any game.

Problem Statement
- Takes a week for leaders to calculate the number of shares
- Calculations using excel is inadequate and can lead to numerous errors
- Some leaders tend to alter the amount of shares that their members are suppose to get (ex:player 1 earned 100 cps but only get 50)

Background Information
- Formula for TotalCTFPoints += MemberCTFPoints.get(i) where i is the row value
- Formula for shares: (MemberCTFPoints.get(i) / TotalCTFPoints) * TotalCPShares

Solution Statement
- Created an Java Application that calculates the share that members are suppose to get.

How it works
- When user opens program, they are prompt to enter number of members that played in CSCTF/CTF (UserInputBO)
  - Restraint: Must enter a number otherwise error message will be displayed
- If user inputs number of members successfully, components from jframe will be removed and replaced by SharePanel
  - SharePanel allows user to enter Total CPs up for grabs (reward) and the names and number of CSCTF/CTF points that players earned
  - Click calculate shares button to calculate member shares
  - If user forget an extra member,they can click Add Member button to add a new row to enter member information
  - Restraint: PointField and TotalCPField must be a number. Otherwise error message will display prompting user to enter proper input
  
 It only takes a matter of seconds or minutes to enter the information needed to get the member shares.
 By using this program it benefits botht he leaders and members of the group so that they can get their shares faster.
