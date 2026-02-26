Throughout this project I used the GitHub Copilot and GPT-5 mini AI tools to assist in the creation of Java classes for various 3D shapes, as well as the corresponding JUnit test cases. The AI tools were instrumental in generating code based on the prompts provided, which included specific requirements for constructors, methods, and validation.
It was interesting utilizing AI to generate code, as it allowed for quick iteration and adjustments when I encountered issues such as the AssertionFailedError in my test cases. The AI was able to provide suggestions and fixes that helped me understand the underlying problems and improve my code.
The workflow was generally smooth and quick but the code provided by Co-Pilot GPT-5 mini was not always perfect and required some manual adjustments and validation, especially when it came to ensuring the accuracy of the formulas for volume and surface area. I had to verify these formulas using external tools like Desmos to ensure they were correct.
It also can make the code a little more robust then needed and would have to strip it down a bit to ensure it was not overcomplicating the solution and be very tactful when typing out my prompts. I learned a lot with how
AI reacts to different prompts and how to adjust my prompts to get the desired output. Overall, it was a valuable experience in understanding how AI can assist in software development and the importance of validating and testing the generated code.
It is interesting using AI for the first time with code as I am not used to it and it is a little bit of a learning curve as I am used to typing out my code. I found that using Claude AI was a smoother experience for me
compared to the Co-Pilot GPT-5 mini, as it seemed to provide more accurate and relevant code based on my prompts. However, both tools were helpful in generating code quickly and providing suggestions for improvements when I encountered issues. Overall, it was a valuable learning experience in utilizing AI for coding tasks and understanding the importance of validating and testing the generated code.
But I feel like the experience I had with Claude was overall smoother to utilize and easier to understand the code that I was implementing and when I was reviewing the code before adding it into the IDE. I was able to verify
that all the shape classes were following the same format and that the formulas for volume and surface area were correct. I validated the answers after executing the code with Desmos and online calculator that I used ALL 
through out calculus 1-3, very reliable. I tested and validated each of the individual shape classes to ensure that the numbers provided with the radius I was inputting into the console were correct and that the volume and surface area were accurate. I also tested the JUnit test cases to ensure that they were properly validating the constructors, getters, setters, and calculations for each shape. 
Overall, it was a valuable experience in utilizing AI for coding tasks and understanding the importance of validating and testing the generated code. Though the AI was quick and effective it would struggle on certain prompts 
and the GPT-5 Mini specifically struggled with Step 4 of the project with providing a decent layout of the console to provide user friendly visuals for the user to interact with. 
I was trying to improve my prompt but I was never satisfied with the result and output and ended up utilizing a very similar prompt with Claude AI and it provided a much better output that I was able to implement into my project. 

In my opinion AI is useful for generating code and can be used in the workplace but versus manual coding it can be a bit hairy and NEEDS to be double checked and validated constantly or else you may end up
with a lot of errors and issues that can be time consuming to fix. But at the end of the day you will need to go through the code and add manual changes to it, I do not believe that AI will  at this time
take over manual coding but will be the prominent tool used to generate code and assist in the coding process. 

I ensured that the code that the AI generated was correct by going into the ShapeDriver class and manually creating a Smoketest at the beginning of my project with the main method to validate that the 
entries of my Shape classes were outputting the correct values. These smoketests were very helpful in ensuring that the code that the AI generated was accurate.

With this project it has been really great seeing how the inheritance and polymorphic behavior of the shape classes works when referenced as a Shape3D type. 
Using the ThreeDimensionalShape interface and the Shape3D abstract class allowed for a clean and organized structure for the shape classes, 
and it was interesting to see how the methods from the interface were implemented in each of the shape classes. Seeing the Contract used down to the Shape3D class the Abstract class that extended down to each of the shapes.
When we used the Abstract class to define the methods from the interface we would define the new abstract methods for the subclasses which would utilize its own math for each of the shapes (The subclasses).
The Shape3D class would provide the name and color and use the setters and getters and implmenet them through each shape using inheritance without having to rewrite the same code for each shape class.
From there each of the shape classes would focus on the math and providing a toString() with it's own information but utilizing the boilerplate that was created in the Shape3D class. 

The Polymorphic behavior was amazing to see utilized in the ShapeDriver class when we created an array of of the Shape3D. This right here is the essense of the polymorphic behavior in this project as we were able to store 
all of the different shape classes in a single array and then loop through that array to call the methods for each shape without having to worry about the specific type of shape we were working with. 
This project provided a great opportunity to see how inheritance and polymorphism work together in Java to create a clean and organized structure for our shape classes, and it was interesting to see how the methods from the interface were implemented in each of the shape classes.
I was really happy with how the code generated and get to see a more robust piece of code that AI generated for me to see the behavoriors with polymorphism and inheritance. 




















































